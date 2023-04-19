package week_1.day2;

public class F2_Initialize {
    public static void main(String[] args) {
        // here we'll see about variables in java.
        // initialization is done in F1_DataAndVariables.

        // A variable is the name of a reserved area allocated in memory. In other words, it is a name of the memory
        // location. It is a combination of "vary + able" which means its value can be changed.

        int data = 50;//Here data is variable

        // There are three types of variables in Java:

        // 1) local variable
        // A variable declared inside the body of the method is called local variable. You can use this variable
        // only within that method and the other methods in the class aren't even aware that the variable exists.
        // A local variable cannot be defined with "static" keyword.

        // 2) instance variable
        // A variable declared inside the class but outside the body of the method, is called an instance variable.
        // It is not declared as static.
        // It is called an instance variable because its value is instance-specific and is not shared among instances.

        // 3) static variable
        // A variable that is declared as static is called a static variable. It cannot be local. You can create a
        // single copy of the static variable and share it among all the instances of the class. Memory allocation
        // for static variables happens only once when the class is loaded in the memory.

        int data2 = 50;//instance variable

        // Java Variable Example: Add Two Numbers
        int a = 10;
        int b = 10;
        int c = a + b;
        System.out.println(c);

        // Java Variable Example: Widening (possible to go from bigger datatype to smaller)
        int a1 = 10;
        float f = a1;
        System.out.println(a1);
        System.out.println(f);

        // Java Variable Example: Narrowing (Typecasting) (not possible to go from smaller to bigger)
        float f1 = 10.5f;
        //int a=f;//Compile time error
        int a3 = (int) f1;
        System.out.println(f1);
        System.out.println(a3);

        // Java Variable Example: Overflow
        //Overflow
        int a5=130;
        byte b5=(byte)a5;
        System.out.println(a5);
        System.out.println(b5);

        // Java Variable Example: Adding Lower Type
        byte a7=10;
        byte b7=10;
        //byte c=a+b;//Compile Time Error: because a+b=20 will be int
        byte c7=(byte)(a7+b7);
        System.out.println(c7);
    }

    static int m = 100;//static variable

    void method() {
        int n = 90;//local variable
    }
}
