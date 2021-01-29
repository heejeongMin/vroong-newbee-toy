package com.vroong.newbee.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {"com.vroong.newbee"})
@EnableJpaRepositories(basePackages = {"com.vroong.newbee"} )
public class PersistenceConfiguration {

}
