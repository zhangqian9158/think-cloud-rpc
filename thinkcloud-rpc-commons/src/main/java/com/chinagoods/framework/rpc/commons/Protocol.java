package com.chinagoods.framework.rpc.commons;

import java.util.concurrent.ExecutionException;

/**
 * @author : ZHANG.Q
 * @email : zhangqian9158@gmail.com
 * @date : 2021/12/22 10:57
 */

public interface Protocol {

    void start(URL url) throws InterruptedException;

    String send(URL url,Invocation Invocation) throws ExecutionException, InterruptedException;
}
