package com.lengqi.oauth.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

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

    /**
     * 基于jdbc实现，需要事先在数据库配置客户端信息
     * @return
     */
    public JdbcClientDetailsService clientDetailsService(){
        return new JdbcClientDetailsService(dataSource());
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService());
    }
}
