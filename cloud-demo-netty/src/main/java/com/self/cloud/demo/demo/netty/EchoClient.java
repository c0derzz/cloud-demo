package com.self.cloud.demo.demo.netty;

import com.self.cloud.demo.netty.EchoClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.util.Scanner;

/**
 * Created by liruichuan on 2018/9/11.
 */
public class EchoClient {

    private final String host;
    private final int port;


    public EchoClient( String host,int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception{

        EventLoopGroup group = new NioEventLoopGroup();
        try{

            Bootstrap client = new Bootstrap();
            client.group(group).channel(NioSocketChannel.class).remoteAddress(this.host,this.port).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
                    socketChannel.pipeline().addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
                    socketChannel.pipeline().addLast(new EchoClientHandler());
                }
            });

            ChannelFuture channelFuture = client.connect().sync();
            Scanner sca = new Scanner(System.in);
            while(true){
                String clientMsg = sca.nextLine();
                //如果用户输入退出指令 则退出 关闭客户端
                if("exit".equalsIgnoreCase(clientMsg)){
                    break;
                }
                //发送信息给服务端
                channelFuture.channel().writeAndFlush("siri:"+clientMsg);
            }
            channelFuture.channel().closeFuture().sync();

        }catch(Exception e){

        }finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception{
        new EchoClient("localhost",20000).start();
    }
}
