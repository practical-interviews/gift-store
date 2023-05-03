package com.prime.giftstore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.gemfire.config.annotation.CacheServerApplication;
import org.springframework.data.gemfire.config.annotation.EnableLocator;
import org.springframework.data.gemfire.config.annotation.EnableManager;
import org.springframework.data.gemfire.config.annotation.EnablePdx;
import org.springframework.data.gemfire.function.config.EnableGemfireFunctions;

import static com.prime.giftstore.config.Constants.PROFILE_SERVER;

@Configuration
@CacheServerApplication(
        logLevel = "debug",
        maxConnections = 20,
        port = 42424,
        locators = "localhost[10334]")
@EnableGemfireFunctions
@EnableLocator
@EnableManager
@EnablePdx(persistent = true)
@Profile(value = PROFILE_SERVER)
public class GeodeServerConfig {

}