package com.chinagoods.framework.rpc.register;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.chinagoods.framework.rpc.commons.URL;

/**
 * @author : ZHANG.Q
 * @email : zhangqian9158@gmail.com
 * @date : 2021/12/22 15:43
 */
public class MapRegister {

    private static Map<String, List<URL>> REGISTER = new HashMap<>();

    public static void register(String interfaceName, URL url) {

        List<URL> list = REGISTER.get(interfaceName);

        if (Objects.isNull(list)) {
            list = new ArrayList<>();
        }

        list.add(url);

        REGISTER.put(interfaceName, list);

        saveToFile();
    }


    public static List<URL> get(String interfaceName){

        REGISTER=getFile();
        assert REGISTER != null;
        return REGISTER.get(interfaceName);
    }

    private static void saveToFile() {
        try {

            FileOutputStream fileOutputStream = new FileOutputStream("/register");
            ObjectOutputStream objectOutputStreamn = new ObjectOutputStream(fileOutputStream);
            objectOutputStreamn.writeObject(REGISTER);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, List<URL>> getFile() {

        try {

            FileInputStream fileInputStream = new FileInputStream("/register.txt");
            try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                return (Map<String, List<URL>>) objectInputStream.readObject();
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
