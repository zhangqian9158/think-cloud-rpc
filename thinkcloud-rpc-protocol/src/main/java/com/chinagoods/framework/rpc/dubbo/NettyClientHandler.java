package com.chinagoods.framework.rpc.dubbo;

import com.chinagoods.framework.rpc.commons.Invocation;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

/**
 * @author : ZHANG.Q
 * @email : zhangqian9158@gmail.com
 * @date : 2021/12/23 10:33
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable {

    private ChannelHandlerContext context;
    private Invocation invocation;
    private String result;

    @Override
    public synchronized Object call() throws Exception {
        context.writeAndFlush(this.invocation);
        wait();
        return result;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
       this.context=ctx;
    }

    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) {
        result = msg.toString();
        notify();
    }

    void setInvocation(Invocation invocation){
        this.invocation=invocation;
    }
}
