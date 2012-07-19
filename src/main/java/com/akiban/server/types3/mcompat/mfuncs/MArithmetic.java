/**
 * END USER LICENSE AGREEMENT (“EULA”)
 *
 * READ THIS AGREEMENT CAREFULLY (date: 9/13/2011):
 * http://www.akiban.com/licensing/20110913
 *
 * BY INSTALLING OR USING ALL OR ANY PORTION OF THE SOFTWARE, YOU ARE ACCEPTING
 * ALL OF THE TERMS AND CONDITIONS OF THIS AGREEMENT. YOU AGREE THAT THIS
 * AGREEMENT IS ENFORCEABLE LIKE ANY WRITTEN AGREEMENT SIGNED BY YOU.
 *
 * IF YOU HAVE PAID A LICENSE FEE FOR USE OF THE SOFTWARE AND DO NOT AGREE TO
 * THESE TERMS, YOU MAY RETURN THE SOFTWARE FOR A FULL REFUND PROVIDED YOU (A) DO
 * NOT USE THE SOFTWARE AND (B) RETURN THE SOFTWARE WITHIN THIRTY (30) DAYS OF
 * YOUR INITIAL PURCHASE.
 *
 * IF YOU WISH TO USE THE SOFTWARE AS AN EMPLOYEE, CONTRACTOR, OR AGENT OF A
 * CORPORATION, PARTNERSHIP OR SIMILAR ENTITY, THEN YOU MUST BE AUTHORIZED TO SIGN
 * FOR AND BIND THE ENTITY IN ORDER TO ACCEPT THE TERMS OF THIS AGREEMENT. THE
 * LICENSES GRANTED UNDER THIS AGREEMENT ARE EXPRESSLY CONDITIONED UPON ACCEPTANCE
 * BY SUCH AUTHORIZED PERSONNEL.
 *
 * IF YOU HAVE ENTERED INTO A SEPARATE WRITTEN LICENSE AGREEMENT WITH AKIBAN FOR
 * USE OF THE SOFTWARE, THE TERMS AND CONDITIONS OF SUCH OTHER AGREEMENT SHALL
 * PREVAIL OVER ANY CONFLICTING TERMS OR CONDITIONS IN THIS AGREEMENT.
 */
package com.akiban.server.types3.mcompat.mfuncs;


import com.akiban.server.types3.LazyList;
import com.akiban.server.types3.TClass;
import com.akiban.server.types3.TCustomOverloadResult;
import com.akiban.server.types3.TExecutionContext;
import com.akiban.server.types3.TInstance;
import com.akiban.server.types3.TOverload;
import com.akiban.server.types3.TOverloadResult;
import com.akiban.server.types3.TPreptimeContext;
import com.akiban.server.types3.TPreptimeValue;
import com.akiban.server.types3.common.BigDecimalWrapper;
import com.akiban.server.types3.common.types.NoAttrTClass;
import com.akiban.server.types3.mcompat.mtypes.MBigDecimal.Attrs;
import com.akiban.server.types3.mcompat.mtypes.MNumeric;
import com.akiban.server.types3.pvalue.PValueSource;
import com.akiban.server.types3.pvalue.PValueTarget;
import com.akiban.server.types3.common.funcs.TArithmetic;
import com.akiban.server.types3.mcompat.mtypes.MBigDecimal;
import com.akiban.server.types3.mcompat.mtypes.MBigDecimalWrapper;
import com.akiban.server.types3.mcompat.mtypes.MApproximateNumber;
import com.akiban.server.types3.service.Scalar;

import java.util.List;

public class MArithmetic {   

    private static final int DEC_INDEX = 0;
    private MArithmetic() {}
    
    private static BigDecimalWrapper getWrapper(TExecutionContext context)
    {
        BigDecimalWrapper wrapper = (BigDecimalWrapper)context.exectimeObjectAt(DEC_INDEX);
        // Why would we need a Supplier?
        if (wrapper == null)
            context.putExectimeObject(DEC_INDEX, wrapper = new MBigDecimalWrapper());
        wrapper.reset();
        return wrapper;
    }
    
    // Add functions
    @Scalar
    public static final TOverload ADD_TINYINT = new TArithmetic("plus", MNumeric.TINYINT, MNumeric.MEDIUMINT.instance(5)) {
        @Override
        protected void doEvaluate(TExecutionContext context, LazyList<? extends PValueSource> inputs, PValueTarget output) {
            int a0 = inputs.get(0).getInt8();
            int a1 = inputs.get(0).getInt8();
            output.putInt32(a0 + a1);
        }
    };
    
    public static final TOverload ADD_SMALLINT = new TArithmetic("plus", MNumeric.SMALLINT, MNumeric.MEDIUMINT.instance(7)) {
        @Override
        protected void doEvaluate(TExecutionContext context, LazyList<? extends PValueSource> inputs, PValueTarget output) {
            int a0 = inputs.get(0).getInt16();
            int a1 = inputs.get(0).getInt16();
            output.putInt32(a0 + a1);
        }
    };
     
    public static final TOverload ADD_MEDIUMINT = new TArithmetic("plus", MNumeric.MEDIUMINT, MNumeric.INT.instance(9)) {
        @Override
        protected void doEvaluate(TExecutionContext context, LazyList<? extends PValueSource> inputs, PValueTarget output) {
            int a0 = inputs.get(0).getInt32();
            int a1 = inputs.get(1).getInt32();       
            output.putInt32(a0 + a1);
        }
    };
     
    public static final TOverload ADD_INT = new TArithmetic("plus", MNumeric.INT, MNumeric.BIGINT.instance(12)) {
        @Override
        protected void doEvaluate(TExecutionContext context, LazyList<? extends PValueSource> inputs, PValueTarget output) {
            long a0 = inputs.get(0).getInt32();
            long a1 = inputs.get(1).getInt32();       
            output.putInt64(a0 + a1);
        }
    };
    
    public static final TOverload ADD_BIGINT = new TArithmetic("plus", MNumeric.BIGINT, MNumeric.BIGINT.instance(21)) {
        @Override
        protected void doEvaluate(TExecutionContext context, LazyList<? extends PValueSource> inputs, PValueTarget output) {
            long a0 = inputs.get(0).getInt64();
            long a1 = inputs.get(1).getInt64();
            output.putInt64(a0 + a1);
        }
    };
     
    public static final TOverload ADD_DECIMAL = new DecimalArithmetic("plus") {
        @Override
        protected void doEvaluate(TExecutionContext context, LazyList<? extends PValueSource> inputs, PValueTarget output) {
            output.putObject(getWrapper(context)
                        .add((BigDecimalWrapper)inputs.get(0).getObject())
                        .add((BigDecimalWrapper)inputs.get(1).getObject()));
        }

        @Override
        protected long precisionAndScale(int arg0Precision, int arg0Scale, int arg1Precision, int arg1Scale) {
            return plusOrMinusArithmetic(arg0Precision, arg0Scale, arg1Precision, arg1Scale);
        }
    };
    
    // Subtract functions
    public static final TOverload SUBTRACT_TINYINT = new TArithmetic("minus", MNumeric.TINYINT, MNumeric.INT.instance(5)) {
        @Override
        protected void doEvaluate(TExecutionContext context, LazyList<? extends PValueSource> inputs, PValueTarget output) {
            int a0 = inputs.get(0).getInt8();
            int a1 = inputs.get(0).getInt8();
            output.putInt32(a0 - a1);
        }
    };
    
    public static final TOverload SUBTRACT_SMALLINT = new TArithmetic("minus", MNumeric.SMALLINT, MNumeric.MEDIUMINT.instance(7)) {
        @Override
        protected void doEvaluate(TExecutionContext context, LazyList<? extends PValueSource> inputs, PValueTarget output) {
            int a0 = inputs.get(0).getInt16();
            int a1 = inputs.get(0).getInt16();
            output.putInt32(a0 - a1);
        }
    };
     
    public static final TOverload SUBTRACT_MEDIUMINT = new TArithmetic("minus", MNumeric.MEDIUMINT, MNumeric.INT.instance(9)) {
        @Override
        protected void doEvaluate(TExecutionContext context, LazyList<? extends PValueSource> inputs, PValueTarget output) {
            int a0 = inputs.get(0).getInt32();
            int a1 = inputs.get(1).getInt32();       
            output.putInt32(a0 - a1);
        }
    };
     
   public static final TOverload SUBTRACT_INT = new TArithmetic("minus", MNumeric.INT, MNumeric.BIGINT.instance(12)) {
        @Override
        protected void doEvaluate(TExecutionContext context, LazyList<? extends PValueSource> inputs, PValueTarget output) {
            long a0 = inputs.get(0).getInt32();
            long a1 = inputs.get(1).getInt32();       
            output.putInt64(a0 - a1);
        }
    };
    
   public static final TOverload SUBTRACT_BIGINT = new TArithmetic("minus", MNumeric.BIGINT, MNumeric.BIGINT.instance(21)) {
        @Override
        protected void doEvaluate(TExecutionContext context, LazyList<? extends PValueSource> inputs, PValueTarget output) {
            long a0 = inputs.get(0).getInt64();
            long a1 = inputs.get(1).getInt64();
            output.putInt64(a0 - a1);
        }
    };
     
   public static final TOverload SUBTRACT_DECIMAL = new DecimalArithmetic("minus") { // TODO
        @Override
        protected void doEvaluate(TExecutionContext context, LazyList<? extends PValueSource> inputs, PValueTarget output) {
            output.putObject(getWrapper(context)
                        .add((BigDecimalWrapper)inputs.get(0).getObject())
                        .subtract((BigDecimalWrapper)inputs.get(1).getObject()));
        }

       @Override
       protected long precisionAndScale(int arg0Precision, int arg0Scale, int arg1Precision, int arg1Scale) {
           return plusOrMinusArithmetic(arg0Precision, arg0Scale, arg1Precision, arg1Scale);
       }
    };
    
    // (Regular) Divide functions
   public static final TOverload DIVIDE_TINYINT = new TArithmetic("div", MNumeric.TINYINT, MApproximateNumber.DOUBLE.instance())
    {
        @Override
        protected void doEvaluate(TExecutionContext context, LazyList<? extends PValueSource> inputs, PValueTarget output)
        {
            int divisor = inputs.get(1).getInt8();
            if (divisor == 0)
                output.putNull();
            else
                output.putDouble((double)inputs.get(0).getInt8() / divisor);
        }
    };

   public static final TOverload DIVIDE_SMALLINT = new TArithmetic("div", MNumeric.SMALLINT, MApproximateNumber.DOUBLE.instance())
    {
        @Override
        protected void doEvaluate(TExecutionContext context, LazyList<? extends PValueSource> inputs, PValueTarget output)
        {
            int divisor = inputs.get(1).getInt16();
            if (divisor == 0)
                output.putNull();
            else
                output.putDouble((double)inputs.get(0).getInt16() / divisor);
        }
    };
    
   public static final TOverload DIVIDE_INT = new TArithmetic("div", MNumeric.INT, MApproximateNumber.DOUBLE.instance())
    {
        @Override
        protected void doEvaluate(TExecutionContext context, LazyList<? extends PValueSource> inputs, PValueTarget output)
        {
            int divisor = inputs.get(1).getInt32();
            if (divisor == 0L)
                output.putNull();
            else
                output.putDouble((double)inputs.get(0).getInt32() / divisor);
        }
    };
    
   public static final TOverload DIVIDE_BIGINT = new TArithmetic("div", MNumeric.BIGINT, MApproximateNumber.DOUBLE.instance())
    {
        @Override
        protected void doEvaluate(TExecutionContext context, LazyList<? extends PValueSource> inputs, PValueTarget output)
        {
            long divisor = inputs.get(1).getInt64();
            if (divisor == 0L)
                output.putNull();
            else
                output.putDouble((double)inputs.get(0).getInt64() / divisor);
        }
    };

   public static final TOverload DIVIDE_DOUBLE = new TArithmetic("divide", MApproximateNumber.DOUBLE, MApproximateNumber.DOUBLE.instance())
    {
        @Override
        protected void doEvaluate(TExecutionContext context, LazyList<? extends PValueSource> inputs, PValueTarget output)
        {
            double divisor = inputs.get(1).getDouble();
            if (Double.compare(divisor, 0) == 0)
                output.putNull();
            else
                output.putDouble(inputs.get(0).getDouble() / divisor);
        }
    };

   public static final TOverload DIVIDE_DECIMAL = new DecimalArithmetic("div") { // TODO
        @Override 
        protected void doEvaluate(TExecutionContext context, LazyList<? extends PValueSource> inputs, PValueTarget output) {
            output.putObject(getWrapper(context)
                        .add((BigDecimalWrapper)inputs.get(0).getObject())
                        .divide((BigDecimalWrapper)inputs.get(1).getObject(),
                                 context.outputTInstance().attribute(  // get the scale computed
                                        MBigDecimal.Attrs.SCALE))); // during expr generation time
        }

       @Override
       protected long precisionAndScale(int arg0Precision, int arg0Scale, int arg1Precision, int arg1Scale) {
           throw new UnsupportedOperationException(); // TODO
       }
   };
    
    // Multiply functions
   public static final TOverload MULTIPLY_TINYINT = new TArithmetic("times", MNumeric.TINYINT, MNumeric.INT.instance(7)) {
        @Override
        protected void doEvaluate(TExecutionContext context, LazyList<? extends PValueSource> inputs, PValueTarget output) {
            int a0 = inputs.get(0).getInt8();
            int a1 = inputs.get(1).getInt8();
            output.putInt32(a0 * a1);
        }
    };
    
   public static final TOverload MULTIPLY_SMALLINT = new TArithmetic("times", MNumeric.SMALLINT, MNumeric.INT.instance(11)) {
        @Override
        protected void doEvaluate(TExecutionContext context, LazyList<? extends PValueSource> inputs, PValueTarget output) {
            int a0 = inputs.get(0).getInt16();
            int a1 = inputs.get(1).getInt16();
            output.putInt32(a0 * a1);
        }
    };
    
   public static final TOverload MULTIPLY_MEDIUMINT = new TArithmetic("times", MNumeric.MEDIUMINT, MNumeric.BIGINT.instance(15)) {
        @Override
        protected void doEvaluate(TExecutionContext context, LazyList<? extends PValueSource> inputs, PValueTarget output) {
            long a0 = inputs.get(0).getInt32();
            long a1 = inputs.get(1).getInt32();
            output.putInt64(a0 * a1);
        }
    };
    
   public static final TOverload MULTIPLY_INT = new TArithmetic("times", MNumeric.INT, MNumeric.BIGINT.instance(21)) {
        @Override
        protected void doEvaluate(TExecutionContext context, LazyList<? extends PValueSource> inputs, PValueTarget output) {
            long a0 = inputs.get(0).getInt32();
            long a1 = inputs.get(1).getInt32();
            output.putInt64(a0 * a1);
        }
    };
    
   public static final TOverload MULTIPLY_BIGINT = new TArithmetic("times", MNumeric.BIGINT, MNumeric.BIGINT.instance(39)) {
        @Override
        protected void doEvaluate(TExecutionContext context, LazyList<? extends PValueSource> inputs, PValueTarget output) {
            long a0 = inputs.get(0).getInt64();
            long a1 = inputs.get(1).getInt64();
            output.putInt64(a0 * a1);
        }
    };
    
   public static final TOverload MULTIPLY_DECIMAL = new DecimalArithmetic("times") { // TODO
        @Override
        protected void doEvaluate(TExecutionContext context, LazyList<? extends PValueSource> inputs, PValueTarget output) {
            long a0 = inputs.get(0).getInt64();
            long a1 = inputs.get(1).getInt64();
            output.putInt64(a0 * a1);
        }

       @Override
       protected long precisionAndScale(int arg0Precision, int arg0Scale, int arg1Precision, int arg1Scale) {
           return packPrecisionAndScale(arg0Precision + arg1Precision, arg0Scale + arg1Scale);
       }
   };

   // TODO this should extend some base class that TArithmetic also extends, rather than extending TArithmetic
   // but ignoring its TInstance field
   private abstract static class DecimalArithmetic extends TArithmetic {
       @Override
       public TOverloadResult resultType() {
           return TOverloadResult.custom(new TCustomOverloadResult() {
               @Override
               public TInstance resultInstance(List<TPreptimeValue> inputs, TPreptimeContext context) {
                   TInstance arg0 = inputs.get(0).instance();
                   TInstance arg1 = inputs.get(1).instance();

                   int arg0Precision = arg0.attribute(Attrs.PRECISION);
                   int arg0Scale = arg0.attribute(Attrs.SCALE);

                   int arg1Precision = arg1.attribute(Attrs.PRECISION);
                   int arg1Scale = arg1.attribute(Attrs.SCALE);
                   long resultPrecisionAndScale = precisionAndScale(arg0Precision, arg0Scale, arg1Precision, arg1Scale);
                   int resultPrecision = (int)(resultPrecisionAndScale >> 32);
                   int resultScale = (int)resultPrecisionAndScale;
                   return MNumeric.DECIMAL.instance(resultPrecision, resultScale);
               }
           });
       }

       protected abstract long precisionAndScale(int arg0Precision, int arg0Scale, int arg1Precision, int arg1Scale);

       static long packPrecisionAndScale(int precision, int scale) {
           long result = precision;
           result <<= 32;
           result |= scale;
           return result;
       }

       static long plusOrMinusArithmetic(int arg0Precision, int arg0Scale, int arg1Precision, int arg1Scale){
           int maxScale = Math.max(arg0Scale, arg1Precision);
           int maxPrecision = Math.max(arg0Precision, arg1Precision);
           return packPrecisionAndScale(maxPrecision + maxScale, maxScale);
       }

       protected DecimalArithmetic(String overloadName) {
            super(overloadName, MNumeric.DECIMAL, (TInstance) null);
       }
   }
}