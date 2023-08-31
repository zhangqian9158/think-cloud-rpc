package com.chinagoods.framework.rpc.api.impl;
import com.chinagoods.framework.rpc.api.EchoService;
/**
 * @author : ZHANG.Q
 * @email : zhangqian9158@gmail.com
 * @date : 2021/12/22 17:01
 */
public class EchoServiceImpl implements EchoService {
    @Override
    public String echo(String name) {
        return "hello："+name;
    }
}
