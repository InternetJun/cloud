package org.example.common.core.design.observer;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/11/7 16:21
 */
public class ObserverPatternExample {
    public static void main(String[] args) {
        ConcretSubject subject = new ConcretSubject();

        ConcretObserver observer1 = new ConcretObserver("Observer 1");
        ConcretObserver observer2 = new ConcretObserver("Observer 2");

        subject.registerObserver(observer1);
        subject.registerObserver(observer2);

        subject.setState("New data is available!");
    }
}
