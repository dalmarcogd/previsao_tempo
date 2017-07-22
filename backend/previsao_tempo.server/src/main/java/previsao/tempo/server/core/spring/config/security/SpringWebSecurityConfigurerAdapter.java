package previsao.tempo.server.core.spring.config.security;

import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import previsao.tempo.server.core.hibernate.config.HibernateConfig;
import previsao.tempo.server.core.hibernate.transaction.CustomHibernateTransactionManager;

/**
 * Configuraçao do spring para web e segurança.
 *
 * @author Guilherme Dalmarco (dalmarco.gd@gmail.com)
 */
@EnableWebMvc
@EnableTransactionManagement
@EnableWebSecurity
@Configuration
@ComponentScan(basePackages = "previsao.tempo.server")
public class SpringWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

	@Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private SavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
          .withUser("temporary").password("temporary").roles("ADMIN")
          .and()
          .withUser("user").password("userPass").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .csrf().disable()
        .exceptionHandling()
        .authenticationEntryPoint(restAuthenticationEntryPoint)
        .and()
        .authorizeRequests()
        .antMatchers("/**").authenticated()
        .and()
        .formLogin()
        .successHandler(authenticationSuccessHandler)
        .failureHandler(new SimpleUrlAuthenticationFailureHandler())
        .and()
        .logout();
    }

    @Bean
    public SavedRequestAwareAuthenticationSuccessHandler mySuccessHandler(){
        return new SavedRequestAwareAuthenticationSuccessHandler();
    }
    @Bean
    public SimpleUrlAuthenticationFailureHandler myFailureHandler(){
        return new SimpleUrlAuthenticationFailureHandler();
    }

    //Create a transaction manager
    @Bean
    public HibernateTransactionManager txManager() {
        SessionFactoryImpl sessionFactory = (SessionFactoryImpl) sessionFactory().getObject();
        HibernateTransactionManager htm = new CustomHibernateTransactionManager(sessionFactory);
        htm.setAutodetectDataSource(false);
        return htm;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setPackagesToScan(new String[] { "previsao.tempo.server" });
        sessionFactory.setHibernateProperties(HibernateConfig.getDefaultProperties());
        return sessionFactory;
    }
}