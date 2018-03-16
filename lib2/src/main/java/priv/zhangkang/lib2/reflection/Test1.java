package priv.zhangkang.lib2.reflection;

/**
 * Created on 2018/3/7
 * Title:
 * Description:
 * author 张康
 * update 2018/3/7
 */
public class Test1 extends Test {
    public Test1() {
        System.out.println("子类的构造方法调用了");
    }

    public Test1(String test) {
        System.out.println("子类一个参数的构造方法调用了");
    }

    @Override
    public String toString() {
        return "我是Test1";
    }
}
