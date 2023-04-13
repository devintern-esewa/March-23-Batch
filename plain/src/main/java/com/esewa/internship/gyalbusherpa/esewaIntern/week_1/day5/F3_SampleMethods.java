package week_1.day5;

import java.util.Scanner;

public class F3_SampleMethods {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int number = input.nextInt();

        int result1 = multiplyByTwo(number);
        System.out.println(number + " multiplied by 2 is " + result1);

        int result2 = addNumbers(number, 5);
        System.out.println(number + " plus 5 is " + result2);

        input.close();
    }

    public static int multiplyByTwo(int num) {
        return num * 2;
    }

    public static int addNumbers(int num1, int num2) {
        return num1 + num2;
    }
}
