package org.example.web.config;

import cn.hutool.core.util.ObjectUtil;
import org.example.web.dto.ClientDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/11/25 20:03
 */
public class CustomClientDetails implements ClientDetails {

    private final ClientDto clientDto;

    public CustomClientDetails(ClientDto clientDto) {
        this.clientDto = clientDto;
    }

    private static Set<String> composeFrom(String raw) {
        return Arrays.stream(raw.split(raw, ','))
                .map(m->ObjectUtil.defaultIfEmpty(m, "")).filter(ObjectUtil::isNotEmpty).collect(Collectors.toSet());
    }

    @Override
    public String getClientId() {
        return clientDto.getId();
    }

    @Override
    public Set<String> getResourceIds() {
        return clientDto.getResourceIds();
    }

    @Override
    public boolean isSecretRequired() {
        return true;
    }

    @Override
    public String getClientSecret() {
        return clientDto.getClientSecret();
    }

    @Override
    public boolean isScoped() {
        return true;
    }

    @Override
    public Set<String> getScope() {
        return composeFrom(clientDto.getScope());
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return composeFrom(clientDto.getAuthorizedGrantType());
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return composeFrom(clientDto.getRedirectUri());
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return clientDto.getAuthorities().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return clientDto.getAccessTokenValidity();
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return clientDto.getRefreshTokenValidity();
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return clientDto.isAutoApprove();
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }
}
