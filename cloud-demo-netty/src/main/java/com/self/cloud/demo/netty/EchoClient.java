package com.self.cloud.demo.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

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
                    socketChannel.pipeline().addLast(new EchoClientHandler());
                }
            });

            ChannelFuture channelFuture = client.connect().sync();
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
