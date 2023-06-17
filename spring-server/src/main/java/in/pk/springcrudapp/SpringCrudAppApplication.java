package in.pk.springcrudapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import in.pk.springcrudapp.filters.RestAuthFilter;

@SpringBootApplication
public class SpringCrudAppApplication implements CommandLineRunner{

	@Autowired
	private SpringCrudAppApplicationStartupService startupService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringCrudAppApplication.class, args);
	}
	
	@Override
	public void run(String... args) {
		System.out.println("Production Application Starting up....>");
		startupService.init();
		System.out.println("Production Application Starting Completed....>");

	}
	
	@Bean
	public FilterRegistrationBean<RestAuthFilter> filterRegistrationBean(){
		FilterRegistrationBean<RestAuthFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		RestAuthFilter restAuthFilter = new RestAuthFilter();
		filterRegistrationBean.setFilter(restAuthFilter);
		filterRegistrationBean.addUrlPatterns();
		return filterRegistrationBean;
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
	    return new WebMvcConfigurer() {
	        @Override
	        public void addCorsMappings(CorsRegistry registry) {
	            registry.addMapping("/**")
	                    .allowedOrigins("*");
	        }
	    };
	}

}
