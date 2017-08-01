package kr.or.seongjin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import kr.or.seongjin.reservation.controller.SecurityInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "kr.or.seongjin.reservation.controller", })
public class ServletContextConfig extends WebMvcConfigurerAdapter {
	
	@Value("${spring.resources.static-locations}")
	private String staticResourceLocation;

	@Value("${spring.file.static-locations}")
	private String fileLocation;

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Bean
	public MultipartResolver multipartResolver() {
		org.springframework.web.multipart.commons.CommonsMultipartResolver multipartResolver = new org.springframework.web.multipart.commons.CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(10485760); // 1024 * 1024 * 10 10MB
		return multipartResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations(staticResourceLocation);
		// registry.addResourceHandler("/naver").addResourceLocations("www.naver.com");
		registry.addResourceHandler("/jsp_resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/fd/**").addResourceLocations(fileLocation);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// login interceptor
		registry.addInterceptor(new SecurityInterceptor()).addPathPatterns("/myreservation/**")
				.addPathPatterns("/reservation/**").addPathPatterns("/api/reservation/**")
				.addPathPatterns("/session/**");

		super.addInterceptors(registry);
	}

}
