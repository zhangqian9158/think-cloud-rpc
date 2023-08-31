package com.chinagoods.framework.rpc.nacos;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.listener.Event;
import com.alibaba.nacos.api.naming.listener.EventListener;
import com.alibaba.nacos.api.naming.listener.NamingEvent;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.chinagoods.framework.rpc.commons.URL;

import java.util.ArrayList;
import java.util.List;


/**
 * @author：zhangqian9158@gmail.com
 * @Date: 2023/8/29
 */
public class NacosRegister {

    public static void register(String serivceName, URL url) throws NacosException {


        NamingService naming = NamingFactory.createNamingService("127.0.0.1");
        naming.registerInstance(serivceName, url.getHostname(), url.getPort());
        naming.subscribe(serivceName, new EventListener() {
            @Override
            public void onEvent(Event event) {
                System.out.println(((NamingEvent)event).getServiceName());
                System.out.println(((NamingEvent)event).getInstances());
            }
        });
    }

    public static List<URL> getInstance(String serivceName) throws NacosException {


        List<URL> urls =new ArrayList<>();
        NamingService naming = NamingFactory.createNamingService("127.0.0.1");
        final List<Instance> allInstances = naming.getAllInstances(serivceName);
        for (Instance instance: allInstances) {
            urls.add(new URL(instance.getIp(),instance.getPort()));
        }
        System.out.println(allInstances);
        return urls;

    }
}
