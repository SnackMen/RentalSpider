package com.ws.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.ws.dao.HouseDAOImpl;
import com.ws.dao.IHouseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

/**
 * Created by laowang on 17-4-16.
 */
@Configuration
@Component
@PropertySource("classpath:db.properties")
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class MySQLDataConfiguration implements TransactionManagementConfigurer {

    @Value("${jdbc.driver}")
    private String driver;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.user}")
    private String user;

    @Value("${jdbc.password}")
    private String password;

    @Autowired
    private Environment environment; //get db.properties  must be

    //get db.properties  must be
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigure(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(name = "dataSource")
    public ComboPooledDataSource dataSource(){
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try{
            dataSource.setDriverClass(driver);
            dataSource.setJdbcUrl(url);
            dataSource.setUser(user);
            dataSource.setPassword(password);
            dataSource.setMaxPoolSize(20);
        }catch (Exception e){
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean(name = "getHouseDAO")// tell return is bean
    public IHouseDAO getHouseDAO(){
        return new HouseDAOImpl(dataSource());
    }

    @Bean
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

}
