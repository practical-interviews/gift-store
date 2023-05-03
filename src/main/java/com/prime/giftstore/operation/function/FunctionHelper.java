package com.prime.giftstore.operation.function;

import org.apache.geode.cache.Region;
import org.apache.geode.cache.execute.FunctionContext;
import org.apache.geode.cache.execute.RegionFunctionContext;
import org.apache.geode.cache.partition.PartitionRegionHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FunctionHelper {

    private static final int batchSize = 30000;
    protected static void executeClear(final FunctionContext<?> ctx) {
        if (ctx instanceof RegionFunctionContext) {
            final RegionFunctionContext rfc = (RegionFunctionContext) ctx;
            try {
                final Region<Object, Object> region = rfc.getDataSet();
                if (PartitionRegionHelper.isPartitionedRegion(region)) {
                    executeClear(PartitionRegionHelper.getLocalDataForContext(rfc));
                } else {
                    executeClear(region);
                }
                ctx.getResultSender().lastResult("Success");
            } catch (final Throwable t) {
                rfc.getResultSender().sendException(t);
            }
        } else {
            ctx.getResultSender().lastResult("ERROR: The function must be executed on region!");
        }
    }

    protected static void executeGetAll(final FunctionContext<?> ctx) {
        if (ctx instanceof RegionFunctionContext) {
            final RegionFunctionContext rfc = (RegionFunctionContext) ctx;
            try {
                final Region<Object, Object> region = rfc.getDataSet();
                Collection<Object> values = region.values();
                ctx.getResultSender().lastResult(List.of(values));
            } catch (final Throwable t) {
                rfc.getResultSender().sendException(t);
            }
        } else {
            ctx.getResultSender().lastResult("ERROR: The function must be executed on region!");
        }
    }

    private static void executeClear(final Region<Object, Object> localRegion) {
        int numLocalEntries = localRegion.keySet().size();
        if (numLocalEntries <= batchSize) {
            localRegion.removeAll(localRegion.keySet());
        } else {
            final List<Object> buffer = new ArrayList<>(batchSize);
            int count = 0;
            for (final Object k : localRegion.keySet()) {
                buffer.add(k);
                count++;
                if (count == batchSize) {
                    localRegion.removeAll(buffer);
                    buffer.clear();
                    count = 0;
                }
            }
            localRegion.removeAll(buffer);
        }
    }
}
