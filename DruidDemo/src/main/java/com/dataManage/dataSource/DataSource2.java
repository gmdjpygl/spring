package com.dataManage.dataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration//注解到spring容器中
@MapperScan(basePackages = "com.dataManage.data2.mapper",sqlSessionFactoryRef = "data2SqlSessionFactory")
public class DataSource2 {

    /**
     * 返回data2数据库的数据源
     * @return
     */
    @Bean(name="data2Source")
    @ConfigurationProperties(prefix = "spring.datasource.data2")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    /**
     * 返回data2数据库的会话工厂
     * @param ds
     * @return
     * @throws Exception
     */
    @Bean(name = "data2SqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("data2Source") DataSource ds) throws Exception{
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(ds);
        return bean.getObject();
    }

    /**
     * 返回data2数据库的会话模板
     * @param sessionFactory
     * @return
     * @throws Exception
     */
    @Bean(name = "data2SqlSessionTemplate")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("data2SqlSessionFactory") SqlSessionFactory sessionFactory) throws  Exception{
        return  new SqlSessionTemplate(sessionFactory);
    }

    /**
     * 返回data2数据库的事务
     * @param ds
     * @return
     */
    @Bean(name = "data2TransactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager(@Qualifier("data2Source") DataSource ds){
        return new DataSourceTransactionManager(ds);
    }
}
