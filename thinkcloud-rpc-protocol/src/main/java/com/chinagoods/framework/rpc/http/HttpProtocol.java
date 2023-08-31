package com.chinagoods.framework.rpc.http;


import com.chinagoods.framework.rpc.commons.Invocation;
import com.chinagoods.framework.rpc.commons.Protocol;
import com.chinagoods.framework.rpc.commons.URL;

/**
 * @author : ZHANG.Q
 * @email : zhangqian9158@gmail.com
 * @date : 2021/12/22 11:05
 */
public class HttpProtocol implements Protocol {

    @Override
    public void start(URL url) {
        HttpServer httpServer = new HttpServer();
        httpServer.start(url.getHostname(), url.getPort());
    }

    @Override
    public String send(URL url, Invocation invocation) {
        HttpClient httpClient = new HttpClient();
        return httpClient.send(url.getHostname(), url.getPort(),invocation);
    }
}
