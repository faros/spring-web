package be.faros.springweb.config;


import org.springframework.boot.web.servlet.view.MustacheViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.ViewResolver;

@Configuration
public class WebConfig extends WebSecurityConfigurerAdapter {

/*
    TODO mustache_02
    There are 2 mustache viewResolvers:
        - Non-reactive (Spring Web)
            import org.springframework.boot.web.servlet.view.MustacheViewResolver;

        - Reactive (Spring WebFlux)
            import org.springframework.boot.web.reactive.result.view.MustacheViewResolver;
            (in order to use this one, you would have to switch from spring-web to spring-webflux)

    As you can see both viewResolvers belong to the spring boot project and are not part of spring-web-mvc.
    It's not completely clear why this viewresolver wasn't added to spring-web-mvc.

    ViewResolvers hierarchy in Spring:

          AbstractCachingViewResolver
                 (>> web-mvc)
                       |                                      |
org.thymeleaf.spring4.view.ThymeleafViewResolver     UrlBasedViewResolver
                (>> thymeleaf)                          (>> web-mvc)
                                                              |                              |                        |                   |                          |
                                                  InternalResourceViewResolver    ScriptTemplateViewResolver    TilesViewResolver    XsltViewResolver    AbstractTemplateViewResolver
                                                        (>> web-mvc                        ...                      ...                 ...               ..)        |                         |                           |
                                                                                                                                                             MustacheViewResolver    FreeMarkerViewResolver    GroovyMarkupViewResolver
                                                                                                                                                               (>> spring boot)           (>> web-mvc)                (>> web-mvc)

    Configuring a custom MustacheViewResolver will get spring-boot autoconfig for Mustache off your back and allow you to take full control.
*/
    @Bean
    public ViewResolver getViewResolver(ResourceLoader resourceLoader) {
        //Spring MVC ViewResolver for Mustache
        MustacheViewResolver mustacheViewResolver = new MustacheViewResolver();
        mustacheViewResolver.setPrefix("classpath:/WEB-INF/");
        mustacheViewResolver.setSuffix(".mustache");
        mustacheViewResolver.setCache(false);
        mustacheViewResolver.setExposeRequestAttributes(true);

        return mustacheViewResolver;
    }


    /*
        https://www.baeldung.com/spring-boot-actuators
            Actuator now shares the security config with the regular App security rules. Hence, the security model is dramatically simplified.

        https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/config/web/server/ServerHttpSecurity.html
            A ServerHttpSecurity is similar to Spring Security's HttpSecurity but for WebFlux
     */

    /*
        TODO actuator_security_02b
        If Spring Security is on the classpath and no other WebSecurityConfigurerAdapter is present, the actuators endpoints are secured by Spring Boot auto-config
        If you define a custom WebSecurityConfigurerAdapter (extends), Spring Boot auto-config will back off and you will be in full control of actuator access rules.

    */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
/*
            Starting from Spring Security 4.x, CSRF protection is enabled by default but you can disable it
                https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#csrf

            .csrf().disable()
*/
            .authorizeRequests()
                .mvcMatchers("/actuator/**").permitAll()
                .mvcMatchers("/**").authenticated()
            .and()
/*
            DefaultLoginPageGeneratingFilter is responsible for rendering the default login page (login forms for both normal form login and/or OpenID),
            unless you specify your own login page
*/
            .formLogin()
                .loginPage("/login")
                .permitAll()
        ;
    }

    /*
        TODO actuator_security_02c
        Allows for easily building in memory authentication, LDAP authentication, JDBC based authentication, adding UserDetailsService, and adding AuthenticationProvider's.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("password")
                .roles("USER");
    }
    */
}