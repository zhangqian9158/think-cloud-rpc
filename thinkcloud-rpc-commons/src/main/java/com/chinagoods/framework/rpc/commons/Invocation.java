package com.chinagoods.framework.rpc.commons;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : ZHANG.Q
 * @email : zhangqian9158@gmail.com
 * @date : 2021/12/22 10:54
 */

@Data
@AllArgsConstructor
public class Invocation implements Serializable {

    private String interfaceName;
    private String methodName;
    private Object[] params;
    private Class[] parmaType;
}
