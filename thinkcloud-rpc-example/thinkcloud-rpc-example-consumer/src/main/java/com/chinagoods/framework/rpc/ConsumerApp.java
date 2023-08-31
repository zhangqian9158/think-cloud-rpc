package com.chinagoods.framework.rpc;

import com.chinagoods.framework.rpc.api.EchoService;

import java.util.Scanner;

/**
 * Hello world!
 */
public class ConsumerApp {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Consumer start ...");
        final EchoService echoService = ProxyFactory.getProxy(EchoService.class);
        String result = echoService.echo("todo");
        System.out.println(result);
        Thread.sleep(1000000);
    }
}
