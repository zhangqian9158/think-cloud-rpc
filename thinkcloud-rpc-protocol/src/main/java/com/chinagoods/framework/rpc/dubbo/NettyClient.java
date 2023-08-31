package com.chinagoods.framework.rpc.dubbo;

import com.chinagoods.framework.rpc.commons.Invocation;
import com.sun.org.apache.bcel.internal.generic.NEW;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * @author : ZHANG.Q
 * @email : zhangqian9158@gmail.com
 * @date : 2021/12/22 14:27
 */
public class NettyClient {

    private NettyClientHandler nettyClientHandler = null;


    public void start(String hostName, Integer port) {

        nettyClientHandler = new NettyClientHandler();

        Bootstrap boot = new Bootstrap();
        EventLoopGroup group = new NioEventLoopGroup();
        boot.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast("decoder", new ObjectDecoder(ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())));
                        pipeline.addLast("encoder", new ObjectEncoder());
                        pipeline.addLast("handler", nettyClientHandler);
                    }
                });
        try {
            boot.connect(hostName, port).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public String send(String hostName, Integer port, Invocation invocation) throws ExecutionException, InterruptedException {

        if (Objects.isNull(nettyClientHandler)) {
            start(hostName, port);
        }

        nettyClientHandler.setInvocation(invocation);
        Object o = clientTheadPool().submit(nettyClientHandler).get();
        return (String) o;

    }

    public static ThreadPoolExecutor clientTheadPool() {
        final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 5, 2, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        return threadPoolExecutor;
    }
}
