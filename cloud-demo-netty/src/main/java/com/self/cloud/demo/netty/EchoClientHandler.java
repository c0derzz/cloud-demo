package com.self.cloud.demo.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * Created by liruichuan on 2018/9/11.
 */
public class EchoClientHandler extends ChannelInboundHandlerAdapter {



    /**
     * 从服务器接收到数据后调用
     * @param channelHandlerContext
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        System.out.println("Client received: " + msg);
    }

    /**
     * 客户端连接服务器后被调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive...");
        //连接服务器之后 向服务器写数据
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks! ", CharsetUtil.UTF_8));
    }

    /**
     * 发生异常时被调用
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
