package org.benf.cfr.reader.bytecode.analysis.parse.lvalue;

import org.benf.cfr.reader.bytecode.analysis.parse.LValue;
import org.benf.cfr.reader.bytecode.analysis.types.discovery.InferredJavaType;
import org.benf.cfr.reader.util.output.Dumper;
import org.benf.cfr.reader.util.output.StdOutDumper;
import org.benf.cfr.reader.util.output.ToStringDumper;

/**
 * Created with IntelliJ IDEA.
 * User: lee
 * Date: 20/07/2012
 * Time: 05:50
 */
public abstract class AbstractLValue implements LValue {
    private InferredJavaType inferredJavaType;

    public AbstractLValue(InferredJavaType inferredJavaType) {
        this.inferredJavaType = inferredJavaType;
    }

    protected String typeToString() {
        return inferredJavaType.toString();
    }

    @Override
    public InferredJavaType getInferredJavaType() {
        return inferredJavaType;
    }

    @Override
    public final String toString() {
        Dumper d = new ToStringDumper();
        this.dump(d);
        return d.toString();
    }
}
