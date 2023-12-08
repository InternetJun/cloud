//package com.ang.filter;
//
//import com.alibaba.fastjson.JSON;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.AbstractAuthenticationToken;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.provider.ClientDetails;
//import org.springframework.security.oauth2.provider.ClientDetailsService;
//import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenEndpointFilter;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.stereotype.Component;
//
///**
// * @Author: lejun
// * @project: cloud
// * @description: 为了使用自定义的 {@link AuthenticationEntryPoint}, 从而自定义发生异常时的响应格式  authenticationManager must be specified
// * @time: 2023/12/6 13:27
// */
//@Component
//@Slf4j
//@AllArgsConstructor
//public class CustomClientCredentialsTokenEndpointFilter extends ClientCredentialsTokenEndpointFilter {
//    public CustomClientCredentialsTokenEndpointFilter(
//            PasswordEncoder passwordEncoder,
//            ClientDetailsService clientDetailsService,
//            AuthenticationEntryPoint authenticationEntryPoint
//    ) {
//        super.setAllowOnlyPost(true);
//        super.setAuthenticationEntryPoint(authenticationEntryPoint);
//        super.setAuthenticationManager(new ClientAuthenticationManager(passwordEncoder, clientDetailsService));
//
//        this.postProcess();
//    }
//
//    private void postProcess() {
//        super.afterPropertiesSet();
//    }
//
//    private static class ClientAuthenticationManager implements AuthenticationManager {
//
//        private final PasswordEncoder passwordEncoder;
//
//        private final ClientDetailsService clientDetailsService;
//
//        public ClientAuthenticationManager(PasswordEncoder passwordEncoder, ClientDetailsService clientDetailsService) {
//            this.passwordEncoder = passwordEncoder;
//            this.clientDetailsService = clientDetailsService;
//        }
//
//        /**
//         * @param authentication {"authenticated":false,"authorities":[],"credentials":"client-a-p","name":"client-a","principal":"client-a"}
//         * @see AuthenticationManager#authenticate(Authentication)
//         */
//        @Override
//        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//            log.debug("Incoming Authentication: {}", JSON.toJSONString(authentication));
//
//            final String clientId = authentication.getName();
//            final ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
//
//            if (!passwordEncoder.matches((CharSequence) authentication.getCredentials(), clientDetails.getClientSecret())) {
//                throw new BadCredentialsException("客户端密码错误!");
//            }
//
//            return new ClientAuthenticationToken(clientDetails);
//        }
//    }
//
//    private static class ClientAuthenticationToken extends AbstractAuthenticationToken {
//
//        private final Object principal;
//
//        private final Object credentials;
//
//        public ClientAuthenticationToken(ClientDetails clientDetails) {
//            super(clientDetails.getAuthorities());
//            this.principal = clientDetails.getClientId();
//            this.credentials = clientDetails.getClientSecret();
//            super.setAuthenticated(true);
//        }
//
//        @Override
//        public Object getCredentials() {
//            return credentials;
//        }
//
//        @Override
//        public Object getPrincipal() {
//            return principal;
//        }
//    }
//}
