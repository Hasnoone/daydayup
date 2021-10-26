package org.example.designpattern.singletonepattern;

/**
 * 单例模式：
 * 1.构造方法私有，确保调用者不能通过构造方法创建对象
 */
public class SingleToneDemo {

    private static volatile SingleToneDemo instance = null;

    private SingleToneDemo() {

    }


    /**
     *
     *  优点:适合脑子简单的人使用这种最蠢的方式
     *  缺点:不支持多线程.所以严格来讲也就不是一个单例莫模式
     * @return
     */
    public static SingleToneDemo getInstance1() {
        if (null == instance) {
            instance = new SingleToneDemo();
        }
        return instance;
    }



    /**
     *
     *  优点:支持多线程
     *  缺点:加入了synchronized同步关键字,影响性能
     * @return
     */
    public synchronized static SingleToneDemo getInstance2() {
        if (null == instance) {
            instance = new SingleToneDemo();
        }
        return instance;
    }


    /**
     *  这也是一种 最笨的
     * public class Singleton {
     *     private static Singleton instance = new Singleton();
     *     private Singleton (){}
     *     public static Singleton getInstance() {
     *     return instance;
     *     }
     * }
     *
     *
     */



    /**
     * 双检索 DCl double-check-locking
     *  优点:支持多线程,还能保证高性能
     *  缺点:
     * @return
     */
    public  static SingleToneDemo getInstance4() {
        if (null == instance) {
            synchronized (SingleToneDemo.class) {
                if (null == instance) {
                    instance = new SingleToneDemo();
                }
            }
        }
        return instance;
    }






    /**
     * z这个跟
     *  优点:
     *  缺点:
     * @return
     */
    private static class SingletonHolder {
        private static final SingleToneDemo INSTANCE = new SingleToneDemo();
    }
    public static SingleToneDemo getInstance5() {
        return SingletonHolder.INSTANCE;
    }


    /**
     * 利用枚举类实现
     */
    public enum Singleton {
        INSTANCE;
        public void doSomething() {
            System.out.println("doSomething");
        }
    }
    public static Singleton getInstance6() {
        return Singleton.INSTANCE;
    }

}
