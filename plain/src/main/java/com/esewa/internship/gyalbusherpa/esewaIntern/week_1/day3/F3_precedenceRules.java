package week_1.day3;

public class F3_precedenceRules {
    public static void main(String[] args) {
        /*
        Java Operator Precedence

            Operator Type	Category	    Precedence

            Unary	        postfix	        expr++ expr--
                            prefix	        ++expr --expr +expr -expr ~ !

            Arithmetic	    multiplicative	* / %
                            additive	    + -

            Shift	        shift   	    << >> >>>

            Relational	    comparison	    < > <= >= instanceof
                            equality	    == !=

            Bitwise	        bitwise AND	    &
                            bitwise
                            exclusive OR	^
                            bitwise
                            inclusive OR	|

            Logical	        logical AND	    &&
                            logical OR	    ||

            Ternary	        ternary	        ? :
            Assignment	    assignment	    = += -= *= /= %= &= ^= |= <<= >>= >>>=
         */

        // Java Arithmetic Operator Example: Expression
        System.out.println(10*10/5+3-1*4/2);
    }
}
