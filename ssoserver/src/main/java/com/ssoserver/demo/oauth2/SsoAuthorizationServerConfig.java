package com.ssoserver.demo.oauth2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class SsoAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    //配置供访问的客户端的账户
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                // 注册一个客户端，设置名称
                .withClient("client1")
                // 设置客户端阴匙
                .secret(new BCryptPasswordEncoder().encode("client1secrect"))
                // 对应客户端登录请求URI
                .redirectUris("http://127.0.0.1:8080/client1/login")
                // 授权方式
                .authorizedGrantTypes("authorization_code", "password", "refresh_token")
                // 授权范围
                .scopes("all")
                // 是否自动同意，如果采用非自动同意，则需要用户手动授权
                .autoApprove(true)
                .and().
                withClient("client2")
                .redirectUris("http://127.0.0.1:8060/client2/login")
                .secret(new BCryptPasswordEncoder().encode("client2secrect"))
                .authorizedGrantTypes("authorization_code", "password", "refresh_token")
                .scopes("all")
                .autoApprove(true);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(jwtTokenStore()).accessTokenConverter(jwtAccessTokenConverter());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("isAuthenticated()");
    }

    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        //指定signkey
        converter.setSigningKey("liuhongdi");
        return converter;
    }

}