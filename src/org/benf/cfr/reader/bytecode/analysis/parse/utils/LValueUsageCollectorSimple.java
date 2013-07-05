package org.benf.cfr.reader.bytecode.analysis.parse.utils;

import org.benf.cfr.reader.bytecode.analysis.parse.LValue;
import org.benf.cfr.reader.util.SetFactory;

import java.util.Collection;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: lee
 * Date: 20/06/2012
 * Time: 06:01
 */
public class LValueUsageCollectorSimple implements LValueUsageCollector {
    private final Set<LValue> used = SetFactory.newSet();

    @Override
    public void collect(LValue lValue) {
        used.add(lValue);
    }

    public Collection<LValue> getUsedLValues() {
        return used;
    }

    public boolean isUsed(LValue lValue) {
        return used.contains(lValue);
    }
}