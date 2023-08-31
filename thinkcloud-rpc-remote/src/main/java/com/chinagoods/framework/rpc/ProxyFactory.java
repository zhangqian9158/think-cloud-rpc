package com.chinagoods.framework.rpc;

import com.chinagoods.framework.rpc.commons.Invocation;
import com.chinagoods.framework.rpc.commons.LoadBalance;
import com.chinagoods.framework.rpc.commons.Protocol;
import com.chinagoods.framework.rpc.commons.URL;
import com.chinagoods.framework.rpc.nacos.NacosRegister;
import com.chinagoods.framework.rpc.register.MapRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @author : ZHANG.Q
 * @email : zhangqian9158@gmail.com
 * @date : 2021/12/23 14:30
 */
public class ProxyFactory<T> {

    public static <T> T getProxy(final Class interfaceClass){

        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                Invocation invocation=new Invocation(interfaceClass.getName(),method.getName(),args,method.getParameterTypes());
                 List<URL> urls = MapRegister.get(interfaceClass.getName());
                 URL url = LoadBalance.random(urls);
                 Protocol protocol = ProtocolFactory.getProtocol();
                 String send = protocol.send(url, invocation);
                return send;
            }
        });
    }
}
