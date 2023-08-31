package com.chinagoods.framework.rpc;
import com.alibaba.nacos.api.exception.NacosException;
import com.chinagoods.framework.rpc.api.EchoService;
import com.chinagoods.framework.rpc.api.impl.EchoServiceImpl;
import com.chinagoods.framework.rpc.commons.Protocol;
import com.chinagoods.framework.rpc.commons.URL;
import com.chinagoods.framework.rpc.nacos.NacosRegister;
import com.chinagoods.framework.rpc.register.LocalRegister;
import com.chinagoods.framework.rpc.register.MapRegister;

/**
 * Hello world!
 *
 */
public class ProviderApp
{
    public static void main( String[] args ) throws InterruptedException, NacosException {
        System.out.println( "dubbo start...." );

        URL url=new URL("127.0.0.1",20881);
        NacosRegister.register(EchoService.class.getName(),url);
        MapRegister.register(EchoService.class.getName(),url);
        LocalRegister.regist(EchoService.class.getName(),EchoServiceImpl.class);
        Protocol protocol= ProtocolFactory.getProtocol();
        protocol.start(url);
    }
}
