package previsao.tempo.server.core.spring.config.mvc;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * Implementação de de configuração do spring, substitui beans.xml
 *
 * @author Guilherme Dalmarco (dalmarco.gd@gmail.com)
 */
@Configuration
public class SpringWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter{

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    	MappingJackson2HttpMessageConverter gsonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
        converters.add(gsonHttpMessageConverter);

        super.configureMessageConverters(converters);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(ManagerInstance.get(ControllerHandlerInterceptorAdapter.class));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
   		registry.addResourceHandler("/**").addResourceLocations("/").setCachePeriod(31556926);
    }
}
