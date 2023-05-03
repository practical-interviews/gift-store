package com.prime.giftstore.operation.function;

import org.apache.geode.cache.execute.Function;
import org.apache.geode.cache.execute.FunctionContext;

public class GetAllFunction implements Function<Object> {

    private static final long serialVersionUID = 11L;

    public void execute(final FunctionContext<Object> ctx) {
        FunctionHelper.executeGetAll(ctx);
    }

    public boolean hasResult() {
        return true;
    }

    public String getId() {
        return this.getClass().getSimpleName();
    }

    public boolean optimizeForWrite() {
        return true;
    }

    public boolean isHA() {
        return true;
    }
}