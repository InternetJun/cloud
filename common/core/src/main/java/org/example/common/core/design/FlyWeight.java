package org.example.common.core.design;


import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lejun
 * @project: cloud
 * @description: 享元模式
 * 典型的例子包括图形系统中的字符绘制，其中每个字符的字体和大小可以作为内部状态，而位置等属性可以作为外部状
 * @time: 2023/9/2 16:24
 */
public class FlyWeight {

    // 享元工厂类

    /**
     * <p>
     * 在享元模式中，有两个关键的概念：
     * 内部状态（Intrinsic State）：内部状态是可以被多个享元对象共享的状态，它通常是不变的，存储在享元对象内部。内部状态是享元对象的一部分，因此不会随每个对象的变化而变化。
     * 外部状态（Extrinsic State）：外部状态是每个享元对象的特有状态，它不能被共享。外部状态存储在客户端代码中，而不是享元对象内部。
     * 享元模式的距离取决于您的需求和项目的规模。在小型项目中，可能不太需要使用享元模式，
     * 因为内存开销可能不是主要问题。但是，在处理大规模数据或需要频繁创建和销毁对象的情况下
     * ，享元模式可以大幅减少内存占用，提高性能。因此，它更常用于需要优化内存和性能的大型应用程序或系统中。
     * </p>
     */
    class StringFlyweightFactory {
        private Map<String, StringFlyweight> flyweights = new HashMap<>();

        public StringFlyweight getStringFlyweight(String value) {
            if (!flyweights.containsKey(value)) {
                flyweights.put(value, new StringFlyweight(value));
            }
            return flyweights.get(value);
        }
    }

    // 享元类
    class StringFlyweight {
        private String value;

        public StringFlyweight(String value) {
            this.value = value;
        }

        public void printLocation(int x, int y) {
            System.out.println("String '" + value + "' is located at (" + x + ", " + y + ")");
        }
    }

    @Test
    public void main() {
        StringFlyweightFactory factory = new StringFlyweightFactory();

        // 创建多个享元对象，共享相同的内部状态
        StringFlyweight hello = factory.getStringFlyweight("Hello");
        StringFlyweight world = factory.getStringFlyweight("World");

        // 在不同位置使用这些享元对象的外部状态
        hello.printLocation(10, 20);
        world.printLocation(30, 40);
        hello.printLocation(50, 60);
    }
}
