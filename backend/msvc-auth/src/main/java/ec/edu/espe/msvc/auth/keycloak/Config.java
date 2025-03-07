package ec.edu.espe.msvc.auth.keycloak;

import ec.edu.espe.msvc.auth.keycloak.providers.SimplePlatformProvider;
import lombok.RequiredArgsConstructor;
import org.jboss.resteasy.plugins.server.servlet.HttpServlet30Dispatcher;
import org.jboss.resteasy.plugins.server.servlet.ResteasyContextParameters;
import org.keycloak.platform.Platform;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.naming.*;
import javax.naming.spi.NamingManager;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

@Configuration
@RequiredArgsConstructor
public class Config {

	private final ServerProperties properties;
	private final DataSource dataSource;

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(List.of(
				"http://localhost:4200",
				"http://localhost",
				"http://20.55.108.68"
		));
		config.addAllowedHeader("*");
		config.setAllowedMethods(
				Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH", "HEAD")
		);
		source.registerCorsConfiguration("/**", config);
		return source;
	}

	@Bean
	@ConditionalOnMissingBean(name = "springBootPlatform")
	protected SimplePlatformProvider springBootPlatform() {
		return (SimplePlatformProvider) Platform.getPlatform();
	}

	@Bean
	ServletRegistrationBean<HttpServlet30Dispatcher> keycloakJaxRsApplication() {
		try {
			mockJndiEnvironment();
		} catch (NamingException ex) {
			Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
		}
		App.properties = properties;
		final var servlet = new ServletRegistrationBean<HttpServlet30Dispatcher>(new HttpServlet30Dispatcher());
		servlet.addInitParameter("jakarta.ws.rs.Application", App.class.getName());
		servlet.addInitParameter(ResteasyContextParameters.RESTEASY_SERVLET_MAPPING_PREFIX, properties.contextPath());
		servlet.addInitParameter(ResteasyContextParameters.RESTEASY_USE_CONTAINER_FORM_PARAMS, "true");
		servlet.addUrlMappings(properties.contextPath() + "/*");
		servlet.setLoadOnStartup(2);
		servlet.setAsyncSupported(true);
		return servlet;
	}

	@Bean
	FilterRegistrationBean<RequestFilter> keycloakSessionManagement() {
		final var filter = new FilterRegistrationBean<RequestFilter>();
		filter.setName("Keycloak Session Management");
		filter.setFilter(new RequestFilter());
		filter.addUrlPatterns(properties.contextPath() + "/*");
		return filter;
	}

	private void mockJndiEnvironment() throws NamingException {
		NamingManager.setInitialContextFactoryBuilder((env) -> (environment) -> new InitialContext() {
			@Override
			public Object lookup(Name name) {
				return lookup(name.toString());
			}

			@Override
			public Object lookup(String name) {
				if ("spring/datasource".equals(name)) {
					return dataSource;
				} else if (name.startsWith("java:jboss/ee/concurrency/executor/")) {
					return fixedThreadPool();
				}
				return null;
			}

			@Override
			public NameParser getNameParser(String name) {
				return CompositeName::new;
			}

			@Override
			public void close() {
			}
		});
	}

	@Bean("fixedThreadPool")
	ExecutorService fixedThreadPool() {
		return Executors.newFixedThreadPool(5);
	}

}
