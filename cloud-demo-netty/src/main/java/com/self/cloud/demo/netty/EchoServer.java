package com.self.cloud.demo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * Created by liruichuan on 2018/9/8.
 * 服务器
 */
public class EchoServer {

    private int port;

    public EchoServer(int port) {
        this.port = port;
    }

    /**
     * 启动服务器
     */
    public void start() throws Exception{

        EventLoopGroup group = new NioEventLoopGroup();

        try{
            ServerBootstrap server  = new ServerBootstrap();
            server.group(group).channel(NioServerSocketChannel.class).localAddress(port).childHandler(new ChannelInitializer<Channel>() {
                @Override
                protected void initChannel(Channel channel) throws Exception {
                    channel.pipeline().addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
                    channel.pipeline().addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
                    channel.pipeline().addLast(new EchoServerHandler());
                }
            });
            //绑定服务器 阻塞 直到绑定服务器完成
            ChannelFuture channelFuture = server.bind().sync();
            System.out.println(this.getClass().getName()+" started and listen on port " + channelFuture.channel().localAddress());
            channelFuture.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception{
        new EchoServer(20000).start();
    }
}
