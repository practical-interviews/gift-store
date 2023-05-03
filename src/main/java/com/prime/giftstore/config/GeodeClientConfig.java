package com.prime.giftstore.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.config.annotation.EnablePdx;

import static com.prime.giftstore.config.Constants.PROFILE_CLIENT;

@Configuration
@ClientCacheApplication(servers = {@ClientCacheApplication.Server(port = 42424)})
@EnablePdx(persistent = true)
@EnableCaching
@Profile(value = PROFILE_CLIENT)
public class GeodeClientConfig {


}
