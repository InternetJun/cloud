package org.example.common.core.design;

import org.junit.Test;

/**
 * @Author: lejun
 * @project: cloud
 * @description: 门面模式。对多个小功能的封装处理。
 * Spring MVC框架：Spring MVC的DispatcherServlet可以看作是一个门面，它隐藏了底层的Servlet容器和HTTP请求处理的复杂性。它将请求分发给控制器，使开发人员可以更专注于处理业务逻辑。
 *
 * Spring JDBC框架：Spring的JdbcTemplate类是对JDBC的一个门面，它简化了与数据库的交互，隐藏了许多繁琐的JDBC代码，如连接管理、异常处理等。
 *
 * Spring事务管理：Spring的事务管理也使用门面模式。PlatformTransactionManager是一个接口，它提供了一个通用的事务管理接口，隐藏了不同事务管理器的具体实现细节，如JDBC事务、JTA事务等。
 *
 * Spring Security：Spring Security框架提供了一组门面，用于处理身份验证和授权。它隐藏了底层的安全机制的复杂性，使应用程序更容易实现安全性。
 *
 * Spring Boot：Spring Boot是Spring的一个子项目，它提供了一个快速构建应用程序的方式。Spring Boot的自动配置和起步依赖可以看作是门面，它们隐藏了大量的配置和依赖管理，使开发人员更容易创建Spring应用程序。
 *
 * 这些示例中，Spring框架提供了门面，用于封装底层复杂性，使开发人员能够更方便地使用Spring的各种功能，同时提高了代码的可维护性和可读性。
 * @time: 2023/9/2 16:18
 */
public class Faceor {
    // 子系统组件类
    class Projector {
        public void turnOn() {
            System.out.println("投影仪已开启");
        }

        public void turnOff() {
            System.out.println("投影仪已关闭");
        }
    }

    class SoundSystem {
        public void turnOn() {
            System.out.println("音响已开启");
        }

        public void turnOff() {
            System.out.println("音响已关闭");
        }
    }

    class DVDPlayer {
        public void turnOn() {
            System.out.println("DVD播放器已开启");
        }

        public void turnOff() {
            System.out.println("DVD播放器已关闭");
        }
    }

    // 家庭影院门面类
    class HomeTheaterFacade {
        private Projector projector;
        private SoundSystem soundSystem;
        private DVDPlayer dvdPlayer;

        public HomeTheaterFacade() {
            projector = new Projector();
            soundSystem = new SoundSystem();
            dvdPlayer = new DVDPlayer();
        }

        public void watchMovie() {
            System.out.println("准备观看电影...");
            projector.turnOn();
            soundSystem.turnOn();
            dvdPlayer.turnOn();
        }

        public void endMovie() {
            System.out.println("电影结束...");
            projector.turnOff();
            soundSystem.turnOff();
            dvdPlayer.turnOff();
        }
    }

    @Test
    public void main() {
        // 创建家庭影院门面对象
        HomeTheaterFacade homeTheater = new HomeTheaterFacade();

        // 用户只需与门面类交互，而无需了解内部组件的复杂性
        homeTheater.watchMovie();
        System.out.println("---------------");
        homeTheater.endMovie();
    }

}
