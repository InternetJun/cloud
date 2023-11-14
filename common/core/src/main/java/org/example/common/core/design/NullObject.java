package org.example.common.core.design;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

import java.util.ArrayList;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/11/2 13:27
 */
public class NullObject {


    public static class TestNotNull {
        private NullObject nullObject = new NullObject();
        private int a;

        public static void main(String[] args) {
//            System.out.println(ClassLayout.parseInstance(new ArrayList<Integer>(10)));
            //打印实例的内存布局
            System.out.println(ClassLayout.parseInstance(new ArrayList<Integer>(10)).toPrintable());
            //打印对象的所有相关内存占用
            System.out.println(GraphLayout.parseInstance(new ArrayList<Integer>(10)).toPrintable());
            //打印对象的所有内存结果并统计
            System.out.println(GraphLayout.parseInstance(new ArrayList<Integer>(10)).toFootprint());
        }
    }
}
