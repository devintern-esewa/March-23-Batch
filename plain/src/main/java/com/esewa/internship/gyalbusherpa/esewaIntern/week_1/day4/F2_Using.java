package week_1.day4;

public class F2_Using {
    public static void main(String[] args) {
        // if statement
        // It is the most basic statement among all control flow statements in Java. It evaluates a Boolean
        // expression and enables the program to enter a block of code if the expression evaluates to true.
        int x = 10;
        int y = 12;
        if (x + y > 20) {
            System.out.println("x + y is greater than 20");
        }

        // if-else statement
        // The if-else statement is an extension to the if-statement, which uses another block of code, i.e., else
        // block. The else block is executed if the condition of the if-block is evaluated as false.
        int x1 = 10;
        int y1 = 12;
        if (x1 + y1 < 10) {
            System.out.println("x1 + y1 is less than 10");
        } else {
            System.out.println("x1 + y1 is greater than 20");
        }


        // Switch case

        /*

        In Java, Switch statements are similar to if-else-if statements. The switch statement contains multiple
        blocks of
        code called cases and a single case is executed based on the variable which is being switched. The switch
        statement
        is easier to use instead of if-else-if statements. It also enhances the readability of the program.

        Points to be noted about switch statement:

        1) The case variables can be int, short, byte, char, or enumeration. String type is also supported since
        version 7 of Java
        2) Cases cannot be duplicate
        3) Default statement is executed when any of the case doesn't match the value of expression. It is optional.
        4) Break statement terminates the switch block when the condition is satisfied.
        5) It is optional, if not used, next case is executed.
        6) While using switch statements, we must notice that the case expression will be of the same type as the
        variable.
        7) However, it will also be a constant value.

         */

        int num = 2;
        switch (num) {
            case 0:
                System.out.println("number is 0");
                break;
            case 1:
                System.out.println("number is 1");
                break;
            default:
                System.out.println(num);
        }

        // Java for loops

        // In Java, for loop is similar to C and C++. It enables us to initialize the loop variable, check the
        // condition, and increment/decrement in a single line of code. We use the for loop only when we exactly know
        // the number of times, we want to execute the block of code.

        int sum = 0;
        for (int j = 1; j <= 10; j++) {
            sum = sum + j;
        }
        System.out.println("The sum of first 10 natural numbers is " + sum);


        // Java for-each loop
        // Java provides an enhanced for loop to traverse the data structures like array or collection. In the
        // for-each loop, we don't need to update the loop variable.
        String[] names = {"Java", "C", "C++", "Python", "JavaScript"};
        System.out.println("Printing the content of the array names:\n");
        for (String name : names) {
            System.out.println(name);
        }

        // Java while loop
        // The while loop is also used to iterate over the number of statements multiple times. However, if we don't
        // know the number of iterations in advance, it is recommended to use a while loop. Unlike for loop, the
        // initialization and increment/decrement doesn't take place inside the loop statement in while loop.
        //
        // It is also known as the entry-controlled loop since the condition is checked at the start of the loop. If
        // the condition is true, then the loop body will be executed; otherwise, the statements after the loop will
        // be executed.

        int i = 0;
        System.out.println("Printing the list of first 10 even numbers \n");
        while (i <= 10) {
            System.out.println(i);
            i = i + 2;
        }

        // Java do-while loop
        // The do-while loop checks the condition at the end of the loop after executing the loop statements. When the
        // number of iteration is not known and we have to execute the loop at least once, we can use do-while loop.
        //
        // It is also known as the exit-controlled loop since the condition is not checked in advance.

        int i2 = 0;
        System.out.println("Printing the list of first 10 even numbers \n");
        do {
            System.out.println(i2);
            i2 = i2 + 2;
        } while (i2 <= 10);
    }
}

