package week_1.day5;

public class F4_VarArgs {

    /*

    The varrags allows the method to accept zero or muliple arguments. Before varargs either we use overloaded method
     or take an array as the method parameter but it was not considered good because it leads to the maintenance
     problem. If we don't know how many argument we will have to pass in the method, varargs is the better approach.

     The varargs uses ellipsis i.e. three dots after the data type. Syntax is as follows:

    return_type method_name(data_type... variableName){}

    Rules for varargs:
    While using the varargs, you must follow some rules otherwise program code won't compile. The rules are as follows:

    There can be only one variable argument in the method.
    Variable argument (varargs) must be the last argument.
     */
    static void don(int d, String... ab) {

        for (String k : ab) {
            System.out.println(k);
        }
    }

    public static void main(String[] args) {

        F4_VarArgs.don(1, "her", "dfa");

    }
}
