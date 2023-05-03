package com.prime.giftstore.operation.function;

import lombok.extern.slf4j.Slf4j;
import org.apache.geode.cache.execute.FunctionContext;
import org.springframework.context.annotation.Profile;
import org.springframework.data.gemfire.function.annotation.GemfireFunction;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile(value = "geode.server")
public class WrapperFunction {

    @GemfireFunction(id = "clearRegionData", hasResult=true, HA=true, optimizeForWrite=true)
    public void clearRegionData(FunctionContext<?> functionContext) {
        log.info("Function Called: ID {}", functionContext.getFunctionId());
        FunctionHelper.executeClear(functionContext);
    }

    @GemfireFunction(id = "getAllRegionData", hasResult=true, HA=true, optimizeForWrite=true)
    public void getAllRegionData(FunctionContext<?> functionContext) {
        log.info("Function Called: ID {}", functionContext.getFunctionId());
        FunctionHelper.executeGetAll(functionContext);
    }
}
