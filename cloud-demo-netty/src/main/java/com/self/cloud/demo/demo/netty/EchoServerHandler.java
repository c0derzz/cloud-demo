package com.self.cloud.demo.demo.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * Created by liruichuan on 2018/9/8.
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter{

    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 新连接的channel （模仿上线操作）
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //新上线的用户
        channels.add(ctx.channel());
        System.out.println(ctx.channel().id()+" add to chat,"+"Online cnt: "+channels.size());
    }

    /**
     * 删除一个连接（模仿下线操作）
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //新上线的用户
        System.out.println(ctx.channel().id()+" left,"+"Online cnt: "+channels.size());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Server received: " + msg);
        for(Channel channel : channels){
            //返回给客户端信息
            channel.write("server return msg:" + msg);
            //刷新缓存区 这里可以自己刷新到客户端 也可以不刷新 但是 需要再channelReadComplete 方法中实现刷新的逻辑 我们这里直接刷新到客户端
            channel.flush();
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //将消息返回到客户端，并且关闭该Channel
        //ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
