package com.fuhaidev.app.biz_common.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.fuhaidev.app.api.service.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xumingxun on 2018/4/28.
 */
@Configuration
@EnableTransactionManagement
public class DruidDataSourceConfig {

    @Value("${spring.datasource.druid.type}")
    private Class<? extends DataSource> dataSourceType;

    @Bean("test1MasterDataSource")
    @ConfigurationProperties(prefix="spring.datasource.druid.test1-master")
    public DataSource test1MasterDataSource(){
        return DataSourceBuilder.create().type(dataSourceType).build();
    }
    @Bean("test1SlaveDataSource")
    @ConfigurationProperties(prefix="spring.datasource.druid.test1-slave")
    public DataSource test1SlaveDataSource(){
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean("test2MasterDataSource")
    @ConfigurationProperties(prefix="spring.datasource.druid.test2-master")
    public DataSource test2MasterDataSource(){
        return DataSourceBuilder.create().type(dataSourceType).build();
    }
    @Bean("test2SlaveDataSource")
    @ConfigurationProperties(prefix="spring.datasource.druid.test2-slave")
    public DataSource test2SlaveDataSource(){
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean("test1DataSource")
    @Primary
    public AbstractRoutingDataSource test1DataSource(){
        MasterSlaveRoutingDataSource proxy = new MasterSlaveRoutingDataSource();
        Map<Object,Object> targetDataResources=new HashMap<Object,Object>();
        targetDataResources.put(DbContextHolder.DbType.MASTER,test1MasterDataSource());
        targetDataResources.put(DbContextHolder.DbType.SLAVE,test1SlaveDataSource());
        proxy.setDefaultTargetDataSource(test1MasterDataSource());
        proxy.setTargetDataSources(targetDataResources);
        proxy.afterPropertiesSet();
        return proxy;
    }

    @Bean("test2DataSource")
    public AbstractRoutingDataSource test2DataSource(){
        MasterSlaveRoutingDataSource proxy = new MasterSlaveRoutingDataSource();
        Map<Object,Object> targetDataResources=new HashMap<Object,Object>();
        targetDataResources.put(DbContextHolder.DbType.MASTER,test2MasterDataSource());
        targetDataResources.put(DbContextHolder.DbType.SLAVE,test2SlaveDataSource());
        proxy.setDefaultTargetDataSource(test2MasterDataSource());
        proxy.setTargetDataSources(targetDataResources);
        proxy.afterPropertiesSet();
        return proxy;
    }

    @Bean(name = "test1MasterTransactionManager")
    public PlatformTransactionManager test1MasterTransactionManager() {
        return new DataSourceTransactionManager(test1DataSource());
    }
    @Bean(name = "test2MasterTransactionManager")
    public PlatformTransactionManager test2MasterTransactionManager() {
        return new DataSourceTransactionManager(test2DataSource());
    }

}
