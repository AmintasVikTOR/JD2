package by.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

//In realease AOP
@Configuration
@ComponentScan("by")
//@EnableLoadTimeWeaving
@EnableAspectJAutoProxy(proxyTargetClass = true) //create bean LogAspect that marked by annotation @Aspect
@Import({by.config.DatasourceConfiguration.class, ApplicationBeanConfiguration.class})
public class ApplicationMainConfig {

}
