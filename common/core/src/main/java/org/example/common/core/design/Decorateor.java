package org.example.common.core.design;

import org.junit.Test;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/9/2 16:09
 */
public class Decorateor {
    // 基础咖啡类
    class Coffee {
        public int cost() {
            return 5;
        }
    }

    // 调料装饰类
    class MilkDecorator extends Coffee {
        private Coffee coffee;

        public MilkDecorator(Coffee coffee) {
            this.coffee = coffee;
        }

        @Override
        public int cost() {
            return coffee.cost() + 2;
        }
    }

    // 另一种调料装饰类
    class SugarDecorator extends Coffee {
        private Coffee coffee;

        public SugarDecorator(Coffee coffee) {
            this.coffee = coffee;
        }

        @Override
        public int cost() {
            return coffee.cost() + 1;
        }
    }

    @Test
    public void main() {
        // 创建一个基础咖啡
        Coffee coffee = new Coffee();
        System.out.println("基础咖啡价格: " + coffee.cost());

        // 加入牛奶装饰
        MilkDecorator milkCoffee = new MilkDecorator(coffee);
        System.out.println("2小杯牛奶的价格: " + 2 * milkCoffee.cost());

        // 再加入糖装饰
        SugarDecorator sugarMilkCoffee = new SugarDecorator(milkCoffee);
        System.out.println("3颗糖的价格: " + 3 * sugarMilkCoffee.cost());

    }
}
