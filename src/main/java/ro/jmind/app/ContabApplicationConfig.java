package ro.jmind.app;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {"ro.jmind.controller", "ro.jmind.app", "ro.jmind.repo", "ro.jmind.service"})
@EntityScan(basePackages = "ro.jmind.model")
@EnableJpaRepositories({"ro.jmind.repo"})
public class ContabApplicationConfig {

//    @Bean
//    public ServletRegistrationBean servletRegistrationBean() {
//        FacesServlet servlet = new FacesServlet();
//        return new ServletRegistrationBean(servlet, "*.jsf");
//    }
//
//    @Bean
//    public FilterRegistrationBean rewriteFilter() {
//        FilterRegistrationBean rwFilter = new FilterRegistrationBean(new RewriteFilter());
//        rwFilter.setDispatcherTypes(EnumSet.of(DispatcherType.FORWARD, DispatcherType.REQUEST,
//                DispatcherType.ASYNC, DispatcherType.ERROR));
//        rwFilter.addUrlPatterns("/*");
//        return rwFilter;
//    }
}