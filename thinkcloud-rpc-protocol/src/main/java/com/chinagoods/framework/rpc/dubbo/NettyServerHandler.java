package com.chinagoods.framework.rpc.dubbo;

import com.chinagoods.framework.rpc.commons.Invocation;
import com.chinagoods.framework.rpc.register.LocalRegister;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.lang.reflect.Method;

/**
 * @author : ZHANG.Q
 * @email : zhangqian9158@gmail.com
 * @date : 2021/12/22 14:57
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {


        Invocation invocation = (Invocation) msg;

        Class serviceImpl = LocalRegister.get(invocation.getInterfaceName());
         Method method = serviceImpl.getMethod(invocation.getMethodName(), invocation.getParmaType());
         Object result = method.invoke(serviceImpl.newInstance(),invocation.getParams());
        System.out.println("消费端发来的数据："+result);
        ctx.writeAndFlush(result);
    }
}
