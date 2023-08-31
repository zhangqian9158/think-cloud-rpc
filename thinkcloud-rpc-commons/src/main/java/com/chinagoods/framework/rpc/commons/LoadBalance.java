package com.chinagoods.framework.rpc.commons;

import java.util.List;
import java.util.Random;

/**
 * @author : ZHANG.Q
 * @email : zhangqian9158@gmail.com
 * @date : 2021/12/23 14:50
 */
public class LoadBalance {

    public static URL random(List<URL> list){
        Random random=new Random();
         int i = random.nextInt(list.size());
        return list.get(i);
    }
}
