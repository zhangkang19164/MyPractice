package priv.zhangkang.lib2.reflection;

/**
 * Created on 2018/3/7
 * Title:
 * Description:
 * author 张康
 * update 2018/3/7
 */
public abstract class Test {
    public String test = "父类初始化";

    public Test() {
        System.out.println("父类的构造方法调用了");
        test="在父类的构造方法中赋值";
    }

    public Test(String test){
        System.out.println("父类一个参数的构造方法调用了");
    }

}
