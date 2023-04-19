package week_1.day2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class F4_Question {
    /* Java has 8 primitive data types;
     char, boolean, byte, short, int, long, float and double
     for this exercise, we'll work with the primitives used to hold integer values(byte, short, int, and long):

     A byte is an 8-bit signed integer.
     A short is a 16-bit signed integer.
     An int is a 32-bit signed integer.
     A long is a 64-bit signed integer.

     Given an input integer, you must determine which primitive data types are capable of properly storing that input.

     sample input 1.
     -150
     sample output1
     -150 can be fitted in: short, int and long

     sample input 2.
     150000
     sample output
     150000 can be fitted in: int and long

     sample input 3
     21333333333333333333333333
     sample output 3
     21333333333333333333333333 can't be fitted anywhere.

     sample input 4
     -1000000000000000
     sample output 4
     -1000000000000000 can be fitted in: long


     */
    public static void main(String[] args) {

        try{
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter a number");
            double b = sc.nextDouble();

            if (b >= Byte.MIN_VALUE && b <= Byte.MAX_VALUE) {
                System.out.println(b + " can be fitted in byte, short, int and long");
                System.out.println("byte is preferred");
            } else if (b >= Short.MIN_VALUE && b <= Short.MAX_VALUE) {
                System.out.println(b + " can be fitted in short, int and long");
                System.out.println("short is preferred");
            } else if (b >= Integer.MIN_VALUE && b <= Integer.MAX_VALUE) {
                System.out.println(b + " can be fitted in int and long");
                System.out.println("int is preferred");
            } else if (b >= Long.MIN_VALUE && b <= Long.MAX_VALUE) {
                System.out.println(b + " can be fitted in long");
                System.out.println("long is preferred");
            } else {
                System.out.println(b + " cannot be fitted anywhere");
            }
        }
        catch (InputMismatchException e){
            System.out.println("Cannot be fitted anywhere");
        }
    }
}
