package com.example.infrastructure.configuration;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;

import javax.sql.DataSource;

@Configuration
@PropertySources(
        value = {
                @PropertySource("classpath:application.yml")
        }
)
@MapperScan("com.example.infrastructure.datasource")
class DataSourceConfiguration {

    @Primary
    @Bean
    @Qualifier("payment")
    DataSource paymentDataSource(@Value("${spring.datasource.url}") String url,
                                 @Value("${spring.datasource.username}") String username,
                                 @Value("${spring.datasource.password}") String password,
                                 @Value("${spring.datasource.driver-class-name}") String driver) {

        return DataSourceBuilder.create().url(url)
                .username(username)
                .password(password)
                .driverClassName(driver)
                .build();
    }

    @Primary
    @Bean("sqlPaymentSessionFactory")
    SqlSessionFactory sqlSearchSessionFactory(@Qualifier("payment") DataSource dataSource) throws Exception {
        return create(dataSource);
    }

    @Primary
    @Bean("sqlPaymentSessionTemplate")
    SqlSessionTemplate sqlPaymentSessionTemplate(@Qualifier("sqlPaymentSessionFactory") SqlSessionFactory sqlSessionFactory) {
        SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return sessionTemplate;
    }

    @Value("${mybatis.mapper-locations}")
    String resource;

    private SqlSessionFactory create(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);

        ResourcePatternResolver resolver = ResourcePatternUtils.getResourcePatternResolver(new DefaultResourceLoader());
        factory.setMapperLocations(resolver.getResources(resource));
        return factory.getObject();
    }

}
