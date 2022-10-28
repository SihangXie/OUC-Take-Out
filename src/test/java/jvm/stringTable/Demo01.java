package jvm.stringTable;

/**
 * @Author: Sihang Xie
 * @Description: 演示StringTable字符串常量池的垃圾回收
 * @Date: 2022/10/16 20:56
 * @Version: 0.0.1
 * @Modified By:
 */
public class Demo01 {
    public static void main(String[] args) {
        int i = 0;
        try {
            // 循环一百次加入StringTable中
            for (int j = 0; j < 20000; j++) {
                String.valueOf(j).intern();
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(i);
        }
    }
}
