package org.example.common.core.design.observer;


/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/11/7 16:14
 */
public interface Subject {
    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();
}
