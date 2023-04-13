package week_1.day3;

public class F2_Working {
    public static void main(String[] args) {

        // Java Arithmetic Operators
        // Java arithmetic operators are used to perform addition, subtraction, multiplication, and division. They act
        // as basic mathematical operations.
        int a = 10;
        int b = 5;
        System.out.println(a + b); //15
        System.out.println(a - b); //5
        System.out.println(a * b); //50
        System.out.println(a / b); //2
        System.out.println(a % b); //0

        // Relational operators in java
        /*

        Java has 6 relational operators.

        == is the equality operator. This returns true if both the operands are referring to the same object,
        otherwise false.
        != is for non-equality operator. It returns true if both the operands are referring to the different objects,
        otherwise false.
        < is less than operator.
        > is greater than operator.
        <= is less than or equal to operator.
        >= is greater than or equal to operator

        */
        int a2 = 10;
        int b2 = 20;

        System.out.println(a2 == b2);  // false
        System.out.println(a2 != b2);  // true
        System.out.println(a2 > b2);  // false
        System.out.println(a2 < b2); // true
        System.out.println(a2 >= b2); //false
        System.out.println(a2 <= b2); //true


        // Java AND Operator Example: Logical && and Bitwise &
        // The logical && operator doesn't check the second condition if the first condition is false. It checks the
        // second condition only if the first one is true.
        //
        // The bitwise & operator always checks both conditions whether first condition is true or false.

        int a3 = 10;
        int b3 = 5;
        int c3 = 20;
        System.out.println(a3 < b3 && a3 < c3);//false && true = false
        System.out.println(a3 < b3 & a3 < c3);//false & true = false

        // Java AND Operator Example: Logical && vs Bitwise &
        int a5 = 10;
        int b5 = 5;
        int c5 = 20;
        System.out.println(a5 < b5 && a5++ < c5);//false && true = false
        System.out.println(a5);//10 because second condition is not checked
        System.out.println(a5 < b5 & a5++ < c5);//false && true = false
        System.out.println(a5);//11 because second condition is checked

        // Java OR Operator Example: Logical || and Bitwise |
        //The logical || operator doesn't check the second condition if the first condition is true. It checks the
        // second condition only if the first one is false.
        //
        //The bitwise | operator always checks both conditions whether first condition is true or false.
        int a6 = 10;
        int b6 = 5;
        int c6 = 20;
        System.out.println(a6 > b6 || a6 < c6);//true || true = true
        System.out.println(a6 > b6 | a6 < c6);//true | true = true
        //|| vs |
        System.out.println(a6 > b6 || a6++ < c6);//true || true = true
        System.out.println(a6);//10 because second condition is not checked
        System.out.println(a6 > b6 | a6++ < c6);//true | true = true
        System.out.println(a6);//11 because second condition is checked

        // Java Ternary Operator
        // Java Ternary operator is used as one line replacement for if-then-else statement and used a lot in Java
        // programming. It is the only conditional operator which takes three operands.

        int a7 = 2;
        int b7 = 5;
        int min = (a7 < b7) ? a7 : b7;
        System.out.println(min);
    }
}
