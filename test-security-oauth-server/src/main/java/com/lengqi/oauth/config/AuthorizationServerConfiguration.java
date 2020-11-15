package com.lengqi.oauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
    /**
     * 基于内存存储令牌
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                //使用内存设置
                .inMemory()
                //client_id
                .withClient("client")
                //client_secret
                .secret("secret")
                //授权类型
                .authorizedGrantTypes("authorization_code")
                //授权范围
                .scopes("app")
                //注册回调地址
                .redirectUris("http://www.baidu.com");
    }
}
