package com.fuhaidev.app.biz_common.util.dataSource;

import com.fuhaidev.app.dao.common.BaseMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by xumingxun on 2018/4/28.
 */
public class DataSourceUtil {
    public static SqlSessionFactory getSqlSessionFactory(DataSource ds,String xmlPath) throws Exception{
        SqlSessionFactoryBean factory=new SqlSessionFactoryBean();
        Resource resource=new ClassPathResource("/mybatis-config.xml");
        factory.setConfigLocation(resource);
        factory.setDataSource(ds);
        factory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(xmlPath));
        return factory.getObject();
    }

    public static MapperScannerConfigurer getMapperScannerConfigurer(String sqlSessionFactory, String basePackage){
        MapperScannerConfigurer mapperScannerConfigurer=new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName(sqlSessionFactory);
        mapperScannerConfigurer.setBasePackage(basePackage);
        Properties properties=new Properties();
        properties.setProperty("mappers", BaseMapper.class.getName());
        properties.setProperty("notEmpty","false");
        properties.setProperty("IDENTITY","MYSQL");
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }

}
