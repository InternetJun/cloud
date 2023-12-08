//package com.example.auth.authentication;
//
//import com.example.auth.core.LoginProcessor;
//import org.example.web.service.impl.DefaultUserImpl;
//import org.springframework.core.log.LogMessage;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import reactor.core.publisher.Mono;
//
//import java.time.Duration;
//
///**
// * @Author: lejun
// * @project: cloud
// * @description:
// * @time: 2023/11/21 16:47
// */
//public class WebReactiveAuthenticationManager extends UserDetailsRepositoryReactiveAuthenticationManager {
//    /**
//     * 定义用户实现服务
//     */
//    private final DefaultUserImpl userDetailsService;
//    /**
//     * 定义登录处理器
//     */
//    private final LoginProcessor loginProcessor;
//
//    public WebReactiveAuthenticationManager(DefaultUserImpl userDetailsService,
//                                            LoginProcessor loginProcessor) {
//        super(userDetailsService);
//        this.userDetailsService = userDetailsService;
//        this.loginProcessor = loginProcessor;
//    }
//
//    /**
//     * 获取用户
//     *
//     * @param username 用户名
//     * @return 用户信息
//     */
//    @Override
//    public Mono<UserDetails> retrieveUser(String username) {
//        //判断是否超过允许最大登录人数
//        return loginProcessor
//                .sessionLimitProcess(username)
//                .filter(isAllow -> isAllow)
//                .then(super.retrieveUser(username));
//    }
//
//    @Override
//    public Mono<Authentication> authenticate(Authentication authentication) {
//        String username = authentication.getName();
//        return super.authenticate(authentication)
//                // 认证成功，删除用户锁定计数
//                .doOnNext(auth -> loginProcessor.removeLockRecord(username).subscribe())
//                .doOnError(BadCredentialsException.class, (ex) -> {
//                    // 打印认证失败日志
//                    logger.debug(LogMessage.format("%s Authentication failed: %s", username, ex.getMessage()));
//                    Duration lockedTime = loginProcessor.getLockedTime();
//                    if (lockedTime.getSeconds() == 0) {
//                        return;
//                    }
//                    // 处理是否要锁定用户
//                    loginProcessor.isLockUser(username).doOnNext(aBoolean -> {
//                        if (aBoolean) {
//                            boolean lock = userDetailsService.lockUser(username);
//                            if (lock) {
//                                userDetailsService.unLockUser(username, lockedTime);
//                            }
//                        }
//                    }).subscribe();
//                });
//    }
//}
