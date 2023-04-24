package week_2.java1point8;

import java.util.function.*;

public class F10_PrimitiveFunctionalInterface {
    /*
        Primitive Predicate Type
            IntPredicate
            DoublePredicate
            LongPredicate

        Primitive Function Type
            DoubleFunction: can take input type as double, return type can be anytype
            IntFunction: can take input type as int, return type can be anytype
            LongFunction: can take input as long

            DoubleToIntFunction --> input type: double, return type: int // method is applyAsInt(double value)
            DoubleToLongFunction --> input type: double, return type: long // method is applyAsLong(double value)

            IntToDoubleFunction --> input type: int, return type: double // method is applyAsDouble(int value)
            IntToLongFunction --> input type: int, return type: long // method is applyAsLong(int value)

            LongToIntFunction --> input type: long, return type: int // method is applyAsInt(long value)
            LongToDoubleFunction --> input type: long, return type: double // method is applyAsDouble(long value)

            ToIntFunction --> input type: any, return type is int // method is applyAsInt(T value)
            ToDoubleFunction --> input type: any, return type is double // method is applyAsDouble(T value)
            ToLongFunction --> input type: any, return type is long // method is applyAsLong(T value)

        Primitive Consumer Type:

            IntConsumer --> takes int value
            DoubleConsumer --> takes double value
            LongConsumer --> takes long value

            ObjDoubleConsumer<T>
                void accept(T t, double value)
            ObjIntConsumer<T>
            ObjLongConsumer<T>

        Primitive Supplier Type:

            BooleanSupplier -->         boolean getAsBoolean()
            IntSupplier -->         int getAsInt()
            DoubleSupplier -->         double getAsDouble()
            LongSupplier -->         long getAsLong()

     */

    /*
    UnaryOperator:
    If input and output are same type then we should go for UnaryOperator
    It is child of Function <T,T>
        IntUnaryOperator:
            public int applyAsInt(int operand)
        LongUnaryOperator:
            public int applyAsLong(long operand)
        DoubleUnaryOperator:
            public int applyAsDouble(double operand)
     */

    /*
    BinaryOperator:
    It is child of BiFunction<T,T,T>
        public T apply(T,T)
     */

    /*
        Primitive type of BinaryOperator
            IntBinaryOperator
            LongBinaryOperator
            DoubleBinaryOperator
     */


    public static void main(String[] args) {
        // IntPrimitive example

        int[] x = {35, 6, 3, 3, 5, 3, 1, 3, 5, 123};
//        Predicate<Integer>
        IntPredicate p = i -> i % 2 == 0;

        for (int value : x) {
//            System.out.println(p.test(value));
        }

        // IntFunction example

//        Function<Integer,Integer>
        IntFunction f = i -> i % 2 == 0;

        IntToDoubleFunction f2 = i -> {
            return Math.sqrt(i);
        };

//        System.out.println(f2.applyAsDouble(81));


//        UnaryOperator<Integer> u = i->i*i;
        IntUnaryOperator r= i->i*i;
//        System.out.println(f.apply(7));
        System.out.println(r.applyAsInt(7));


    }


}
