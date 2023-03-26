package org.example.LoadBalance;

import org.example.common.URL;

import java.util.List;
import java.util.Random;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description:
 * @date 2023/3/26 10:58
 */
public class LoadBalance {
    public static  URL random(List<URL> urlList){
        Random random = new Random();
        int i = random.nextInt(urlList.size());
        return urlList.get(i);
    }
}
