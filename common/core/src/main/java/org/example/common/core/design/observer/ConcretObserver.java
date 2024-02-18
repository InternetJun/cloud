package org.example.common.core.design.observer;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/11/7 16:19
 */
public class ConcretObserver implements Observer{
    private String name;

    public ConcretObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " received a message: " + message);
    }
}
