package kr.or.seongjin.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@ComponentScan(basePackages = {
        "kr.or.seongjin.reservation.dao",
        "kr.or.seongjin.reservation.service"
})
@Import({DbConfig.class})
public class RootApplicationContextConfig {
	
	
}
