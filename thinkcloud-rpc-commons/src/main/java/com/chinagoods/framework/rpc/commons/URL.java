package com.chinagoods.framework.rpc.commons;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : ZHANG.Q
 * @email : zhangqian9158@gmail.com
 * @date : 2021/12/22 10:58
 */

@Data
@AllArgsConstructor
public class URL implements Serializable {

    private String hostname;
    private Integer port;
}
