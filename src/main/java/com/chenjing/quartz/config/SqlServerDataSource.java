package com.chenjing.quartz.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by Chenjing on 2017/5/3.
 */
@Configuration
@MapperScan(basePackages = SqlServerDataSource.PACKAGE)
public class SqlServerDataSource {
    static final String PACKAGE = "com.chenjing.quartz.server.mapper";
    private final static String mapperLocations = "classpath*:mapper/*Mapper.xml";
    private final static String aliasesPackage = "com.chenjing.quartz.server.entity";

    @Value("${mysql_url}")
    private String dbUrl;

    @Value("${mysql_username}")
    private String dbUser;

    @Value("${mysql_password}")
    private String dbPassword;

    @Value("${mysql_initialSize}")
    private String initSize;

    @Value("${mysql_minIdle}")
    private String minIdle;

    @Value("${mysql_maxActive}")
    private String maxActive;

    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUser);
        dataSource.setPassword(dbPassword);
        dataSource.setMinIdle(Integer.valueOf(minIdle));
        dataSource.setInitialSize(Integer.valueOf(initSize));
        dataSource.setMaxActive(Integer.valueOf(maxActive));
        try {
            dataSource.setFilters("stat,wall");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Autowired @Qualifier("dataSource") DataSource rdsDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(rdsDataSource);
        sessionFactory.setTypeAliasesPackage(aliasesPackage);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources(mapperLocations));
        return sessionFactory.getObject();
    }

    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addInitParameter("allow", "127.0.0.1,192.0.0.1");
        //reg.addInitParameter("deny","127.0.0.1,192.0.0.1");
        reg.addUrlMappings("/druid/*");
        reg.addInitParameter("loginUsername", "chenjing");
        reg.addInitParameter("loginPassword", "chenjing");
        return reg;
    }

    /**
     * druid监控过滤
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
