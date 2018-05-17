package com.fuhaidev.app.biz_common.config;

import com.fuhaidev.app.biz_common.util.dataSource.DataSourceUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import javax.sql.DataSource;

/**
 * Created by xumingxun on 2018/4/28.
 */
@Configuration
public class MapperConfig {

    @Bean(name="test1SqlSessionFactory")
    @Primary
    @ConditionalOnClass(DataSource.class)
    public SqlSessionFactory test1SqlSessionFactory(@Autowired @Qualifier("test1DataSource") DataSource ds) throws Exception{
        return DataSourceUtil.getSqlSessionFactory(ds,"classpath*:mybatis/test1/*.xml");
    }

    @Bean(name="test2SqlSessionFactory")
    @ConditionalOnClass(DataSource.class)
    public SqlSessionFactory test2SqlSessionFactory(@Autowired @Qualifier("test2DataSource") DataSource ds) throws Exception{
        return DataSourceUtil.getSqlSessionFactory(ds,"classpath*:mybatis/test2/*.xml");
    }

    @Bean(name="test1MapperScannerConfigurer")
    public MapperScannerConfigurer test1MapperScannerConfigurer(){
        return DataSourceUtil.getMapperScannerConfigurer("test1SqlSessionFactory","com.fuhaidev.app.dao.mapper.test1");
    }
    @Bean(name="test2MapperScannerConfigurer")
    public MapperScannerConfigurer test2MapperScannerConfigurer(){
        return DataSourceUtil.getMapperScannerConfigurer("test2SqlSessionFactory","com.fuhaidev.app.dao.mapper.test2");
    }
}
