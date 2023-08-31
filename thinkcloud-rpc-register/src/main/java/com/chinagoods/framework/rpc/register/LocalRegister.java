package com.chinagoods.framework.rpc.register;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : ZHANG.Q
 * @email : zhangqian9158@gmail.com
 * @date : 2021/12/22 16:38
 */

public class LocalRegister {

    private static Map<String,Class> map= new HashMap<>();

    public static void regist(String interafaceName,Class implClass){
        map.put(interafaceName,implClass);
    }

    public static Class get(String interfaceName){
        return map.get(interfaceName);
    }
}
