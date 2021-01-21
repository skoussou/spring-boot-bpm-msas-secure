package com.redhat.app.configuration.logging;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

/**
 * Filter to log incoming requests
 */
@Configuration
public class RequestLoggingFilterConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(RequestLoggingFilterConfig.class);

	@Bean
	    public CommonsRequestLoggingFilter logFilter() {
		  LOGGER.debug("############ REQUEST FILTER - ACTIVE #############");
		  CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
		  filter.setIncludeQueryString(true);
		  filter.setIncludePayload(true);
		  filter.setMaxPayloadLength(10000);
		  filter.setIncludeHeaders(true);
		  filter.setAfterMessagePrefix("REQUEST DATA : ");
	      return filter;
	    }
	}