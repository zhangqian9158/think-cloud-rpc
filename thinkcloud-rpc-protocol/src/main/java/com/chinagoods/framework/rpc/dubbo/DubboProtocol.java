package com.chinagoods.framework.rpc.dubbo;

import com.chinagoods.framework.rpc.commons.Invocation;
import com.chinagoods.framework.rpc.commons.Protocol;
import com.chinagoods.framework.rpc.commons.URL;

import java.util.concurrent.ExecutionException;

/**
 * @author : ZHANG.Q
 * @email : zhangqian9158@gmail.com
 * @date : 2021/12/22 11:05
 */
public class DubboProtocol implements Protocol {
    @Override
    public void start(URL url) throws InterruptedException {
        NettyServer nettyServer=new NettyServer();
        nettyServer.start(url.getHostname(),url.getPort());
    }

    @Override
    public String send(URL url, Invocation Invocation) throws ExecutionException, InterruptedException {
        NettyClient nettyClient= new NettyClient();
        return nettyClient.send(url.getHostname(),url.getPort(),Invocation);
    }
}
