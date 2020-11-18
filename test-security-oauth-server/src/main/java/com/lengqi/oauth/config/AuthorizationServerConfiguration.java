package com.lengqi.oauth.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
    /**
     * 基于内存存储令牌
     * @param clients
     * @throws Exception
     */
    /**
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
    **/


    /**
     * 基于jdbc存储令牌
     *
     */

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource(){
        // 配置数据源（注意，我使用的是 HikariCP 连接池）
        return DataSourceBuilder.create().build();
    }

    /**
     * 基于jdbc实现，令牌保存到数据库
     * @return
     */
    @Bean
    public TokenStore tokenStore(){
        return new JdbcTokenStore(dataSource());
    }

    @Resource
    private AuthenticationManager authenticationManager;



    /**
     * 基于jdbc实现，需要事先在数据库配置客户端信息
     * @return
     */
    /**
    public JdbcClientDetailsService clientDetailsService(){
        return new JdbcClientDetailsService(dataSource());
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 设置令牌
        endpoints.tokenStore(tokenStore());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //读取客户端配置
        clients.withClientDetails(clientDetailsService());
    }
     */
    /**
     * 基于jwt存储令牌，不用保存，一般设置时间
     * @param
     * @throws Exception
     */

    //使用同一个密钥来编码 JWT 中的  OAuth2 令牌
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("123");
        return converter;
    }
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                // 使用in-memory存储客户端信息
                .inMemory()
                //client_id
                .withClient("client")
                //client_secret
                .secret("secret")
                //授权类型
                .authorizedGrantTypes("authorization_code")
                //授权范围
                .scopes("app")
                .autoApprove(true) ;//登录后绕过批准询问(/oauth/confirm_access)
    }
}
