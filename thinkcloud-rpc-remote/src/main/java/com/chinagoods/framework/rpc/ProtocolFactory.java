package com.chinagoods.framework.rpc;


import com.chinagoods.framework.rpc.commons.Protocol;
import com.chinagoods.framework.rpc.dubbo.DubboProtocol;
import com.chinagoods.framework.rpc.http.HttpProtocol;

/**
 * @author : ZHANG.Q
 * @email : zhangqian9158@gmail.com
 * @date : 2021/12/22 16:56
 */
public class ProtocolFactory {

    public static Protocol getProtocol(){
        return new DubboProtocol();
    }
}
