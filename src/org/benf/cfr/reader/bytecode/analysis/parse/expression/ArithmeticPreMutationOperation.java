package org.benf.cfr.reader.bytecode.analysis.parse.expression;

import org.benf.cfr.reader.bytecode.analysis.parse.Expression;
import org.benf.cfr.reader.bytecode.analysis.parse.LValue;
import org.benf.cfr.reader.bytecode.analysis.parse.StatementContainer;
import org.benf.cfr.reader.bytecode.analysis.parse.rewriters.ExpressionRewriter;
import org.benf.cfr.reader.bytecode.analysis.parse.rewriters.ExpressionRewriterFlags;
import org.benf.cfr.reader.bytecode.analysis.parse.utils.*;
import org.benf.cfr.reader.util.output.Dumper;

/**
 * Created by IntelliJ IDEA.
 * User: lee
 * Date: 16/03/2012
 * <p/>
 * (eg) x >>= 3,  ++x
 */
public class ArithmeticPreMutationOperation extends AbstractMutatingAssignmentExpression {
    private LValue mutated;
    private final ArithOp op;

    public ArithmeticPreMutationOperation(LValue mutated, ArithOp op) {
        super(mutated.getInferredJavaType());
        this.mutated = mutated;
        this.op = op;
    }

    @Override
    public Dumper dump(Dumper d) {
        return d.print(op == ArithOp.PLUS ? "++" : "--").dump(mutated);
    }

    @Override
    public Expression replaceSingleUsageLValues(LValueRewriter lValueRewriter, SSAIdentifiers ssaIdentifiers, StatementContainer statementContainer) {
        return this;
    }

    @Override
    public Expression applyExpressionRewriter(ExpressionRewriter expressionRewriter, SSAIdentifiers ssaIdentifiers, StatementContainer statementContainer, ExpressionRewriterFlags flags) {
        return this;
    }

    @Override
    public boolean isSelfMutatingOp1(LValue lValue, ArithOp arithOp) {
        return this.mutated.equals(lValue) &&
                this.op == arithOp;
    }

    @Override
    public ArithmeticPostMutationOperation getPostMutation() {
        return new ArithmeticPostMutationOperation(mutated, op);
    }

    @Override
    public void collectUsedLValues(LValueUsageCollector lValueUsageCollector) {
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ArithmeticPreMutationOperation)) return false;

        ArithmeticPreMutationOperation other = (ArithmeticPreMutationOperation) o;

        return mutated.equals(other.mutated) &&
                op.equals(other.op);
    }
}
