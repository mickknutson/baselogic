package com.baselogic.tutorials.configuration.dev;

import com.jolbox.bonecp.BoneCPDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.SimpleLoadTimeWeaver;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "com.baselogic.bfl.repositories.jpa")
@PropertySource("classpath:application.properties")
//@ComponentScan(basePackages = {"com.baselogic.bfl.domain"})
@EnableTransactionManagement
//@EnableTransactionManagement//(mode = AdviceMode.ASPECTJ, proxyTargetClass = false)
@Profile("dev")
@SuppressWarnings("unused")
public class DevDatabaseConfiguration {

	private static final Logger logger = LoggerFactory
            .getLogger(DevDatabaseConfiguration.class);

//    @Resource
    @Autowired
    private Environment environment;

    protected static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driverClass";
    protected static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
    protected static final String PROPERTY_NAME_DATABASE_URL = "db.url";
    protected static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";

    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    private static final String PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    private static final String PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY = "hibernate.ejb.naming_strategy";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";

    private static final String PROPERTY_LOG_STATEMENTS_ENABLED = "db.logStatementsEnabled";

    private static final String PROPERTY_PACKAGES_TO_SCAN = "com.baselogic.bfl.domain";



    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public PersistenceExceptionTranslator persistenceExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }

    /*
    @Bean
    public PersistenceExceptionTranslator persistenceExceptionTranslator() {
        return new HibernateJpaDialect();
    }
    */


    @Bean
    public LocalValidatorFactoryBean validator(){
        return new LocalValidatorFactoryBean();
    }

    class CustomClassLoaderWeaver extends SimpleLoadTimeWeaver {
        @Override
        public ClassLoader getInstrumentableClassLoader() {
            return super.getInstrumentableClassLoader().getParent();
        }
    }

    @Bean
    public SimpleLoadTimeWeaver loadTimeWeaver() {
        return new CustomClassLoaderWeaver();
    }


    /*@Bean
    public JpaTransactionManager transactionManager() throws Exception{
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
            entityManagerFactory().getObject());
        // Must call afterPropertiesSet() because this is a InitializingBean
        transactionManager.afterPropertiesSet();
        return transactionManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactoryBean.setDataSource(dataSource());

        //entityManagerFactoryBean.setPersistenceXmlLocation("classpath:/META-INF/persistence.xml");
        //TODO FIXME: Cannot auto scan right now because of persistence.xml setting:
        //<exclude-unlisted-classes>true</exclude-unlisted-classes>
        //entityManagerFactoryBean.setPackagesToScan("com.comcast.oxygen.codewash");

        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());

        entityManagerFactoryBean.setLoadTimeWeaver(loadTimeWeaver());

        // Must call afterPropertiesSet() because this is a InitializingBean
        entityManagerFactoryBean.afterPropertiesSet();
        return entityManagerFactoryBean;
    }

    //@Bean
    public JpaVendorAdapter jpaVendorAdapterOpenJpa() {
        OpenJpaVendorAdapter jpaVendorAdapter = new OpenJpaVendorAdapter();
        jpaVendorAdapter.setDatabasePlatform("org.apache.openjpa.jdbc.sql.H2Dictionary");
        //jpaVendorAdapter.setShowSql(environment.getProperty(PROPERTY_DATABASE_SHOW_SQL, Boolean.class, false));
        return jpaVendorAdapter;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        EclipseLinkJpaVendorAdapter jpaVendorAdapter = new EclipseLinkJpaVendorAdapter();
        // TODO FIXME: Need to externalize:
        jpaVendorAdapter.setDatabasePlatform("org.eclipse.persistence.platform.database.H2Platform"); //HSQL
        return jpaVendorAdapter;
    }*/

    @Bean
    public JpaTransactionManager transactionManager() {
        logger.info("Loading Transaction Manager...");
        JpaTransactionManager transactionManager = new JpaTransactionManager();

        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        logger.info("Loading Entity Manager...");
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        entityManagerFactoryBean.setLoadTimeWeaver(loadTimeWeaver());

        entityManagerFactoryBean.setPackagesToScan(PROPERTY_PACKAGES_TO_SCAN);

        Properties jpaProperties = new Properties();
        jpaProperties.put(PROPERTY_NAME_HIBERNATE_DIALECT, environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
        jpaProperties.put(PROPERTY_NAME_HIBERNATE_FORMAT_SQL, environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_FORMAT_SQL));
        jpaProperties.put(PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO, environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO));
        jpaProperties.put(PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY, environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY));
        jpaProperties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));

        entityManagerFactoryBean.setJpaProperties(jpaProperties);

        return entityManagerFactoryBean;
    }

    /*@Bean
    public JpaVendorAdapter jpaVendorAdapterOpenJpa() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabasePlatform("org.apache.openjpa.jdbc.sql.H2Dictionary");
        //jpaVendorAdapter.setShowSql(environment.getProperty(PROPERTY_DATABASE_SHOW_SQL, Boolean.class, false));
        return jpaVendorAdapter;
    }*/


    /**
     * Look at:
     * https://github.com/pkainulainen/spring-data-jpa-examples/blob/master/integration-testing/pom.xml
     *
     * @return
     */
    @Bean
    public DataSource dataSource() {
        BoneCPDataSource dataSource = new BoneCPDataSource();

        dataSource.setDriverClass(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
        dataSource.setJdbcUrl(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
        dataSource.setUsername(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
        dataSource.setPassword(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));
        dataSource.setLogStatementsEnabled(environment.getRequiredProperty(PROPERTY_LOG_STATEMENTS_ENABLED, Boolean.class));

        return dataSource;
    }

}
