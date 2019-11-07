package com.self.cloud.demo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * Created by liruichuan on 2018/9/11.
 */
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    /**
     * 从服务器接收到数据后调用
     * @param channelHandlerContext
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf msg) throws Exception {
        System.out.println("Client received: " + ByteBufUtil.hexDump(msg.readBytes(msg.readableBytes())));
    }

    /**
     * 客户端连接服务器后被调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //连接服务器之后 向服务器写数据
        ctx.write(Unpooled.copiedBuffer("Netty rocks! ", CharsetUtil.UTF_8));
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
