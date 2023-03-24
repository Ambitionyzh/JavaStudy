package org.example.register;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description:
 * @date 2023/3/24 22:30
 */
public class LocalRegister {
    private static Map<String,Class> map = new HashMap<>();

    public static void regist(String interfaceName,String version,Class implClass){
        map.put(interfaceName+version,implClass);
    }
    public static Class get(String interfaceName,String version){
        return map.get(interfaceName+version);
    }
}
