package com.classsyn;

/**
 * Created by houbin on 17-3-18.
 */
class Dog1 {
    public synchronized void foo(Dog2 dog2) {
        System.out.println("狗狗" + Thread.currentThread().getName()
                + "叫：汪汪");     // ①
        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("狗狗" + Thread.currentThread().getName()
                + "叫：汪汪汪汪");    // ③
        dog2.last();
    }
    public synchronized void last() {
        System.out.println("两只狗狗一起叫：汪汪汪汪");
    }
}
 class  Dog2 {
    public synchronized void bar(Dog1 dog1) {
        System.out.println("狗狗" + Thread.currentThread().getName()
                + "叫：汪汪");   // ②
        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("狗狗" + Thread.currentThread().getName()
                + "叫：汪汪汪汪");  // ④
        dog1.last();
    }
    public synchronized void last() {
        System.out.println("两只狗狗一起叫：汪汪汪汪");
    }
}

public class test implements Runnable {
    Dog1 dog1 = new Dog1();
    Dog2 dog2 = new Dog2();
    public void init() {
        Thread.currentThread().setName("1");
        dog1.foo(dog2);
        System.out.println("狗狗"+Thread.currentThread().getName()+"不叫了");
    }
    public void run() {
        Thread.currentThread().setName("2");
        dog2.bar(dog1);
        System.out.println("狗狗"+Thread.currentThread().getName()+"不叫了");
    }
    public static void main(String[] args) {
        test dl = new test();
        new Thread(dl).start();
        dl.init();
    }
}



