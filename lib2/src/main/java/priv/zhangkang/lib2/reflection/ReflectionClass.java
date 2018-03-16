package priv.zhangkang.lib2.reflection;

/**
 * Created on 2018/3/7
 * Title:
 * Description:
 * author 张康
 * update 2018/3/7
 */
public class ReflectionClass {
    private String mString1;
    private MyTest mMyTest;

    public ReflectionClass() {
        mMyTest = new MyTest();
    }

    public String getString1() {
        return mString1;
    }

    public void setString1(String string1) {
        mString1 = string1;
    }

    private class MyTest {
        private String mString = "私有";
    }


}
