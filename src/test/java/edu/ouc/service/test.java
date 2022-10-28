package edu.ouc.service;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * @Author: Sihang Xie
 * @Description:
 * @Date: 2022/10/9 19:58
 * @Version: 0.0.1
 * @Modified By:
 */
public class test {
    @Test
    public void testOutOfMemory() {
        int i = 0;
        try {
            ArrayList<String> list = new ArrayList<>();
            String a = "hello";
            while (true) {
                list.add(a);
                a += a;
                i++;
            }
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println(i);
        }
    }

    @Test
    public void testGC() throws InterruptedException {
        System.out.println("1...");
        Thread.sleep(50000);
        byte[] array = new byte[1024 * 1024 * 10];  // 10MB
        System.out.println("2...");
        Thread.sleep(20000);
        array = null;   // 取消byte数组的引用
        System.gc();    // 调用垃圾回收器
        System.out.println("3...");
        Thread.sleep(1000000L);
    }
}
