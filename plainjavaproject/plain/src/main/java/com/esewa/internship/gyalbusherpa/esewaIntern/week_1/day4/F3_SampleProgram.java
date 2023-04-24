package week_1.day4;

import java.util.Scanner;

public class F3_SampleProgram {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // if-else statement
        System.out.print("Enter a number: ");
        int number = input.nextInt();

        if (number > 0) {
            System.out.println("The number is positive.");
        } else if (number < 0) {
            System.out.println("The number is negative.");
        } else {
            System.out.println("The number is zero.");
        }

        // switch statement
        System.out.print("Enter a month number (1-12): ");
        int month = input.nextInt();

        switch (month) {
            case 1:
                System.out.println("January");
                break;
            case 2:
                System.out.println("February");
                break;
            case 3:
                System.out.println("March");
                break;
            case 4:
                System.out.println("April");
                break;
            case 5:
                System.out.println("May");
                break;
            case 6:
                System.out.println("June");
                break;
            case 7:
                System.out.println("July");
                break;
            case 8:
                System.out.println("August");
                break;
            case 9:
                System.out.println("September");
                break;
            case 10:
                System.out.println("October");
                break;
            case 11:
                System.out.println("November");
                break;
            case 12:
                System.out.println("December");
                break;
            default:
                System.out.println("Invalid month number.");
                break;
        }

        // while loop
        int i = 1;
        while (i <= 5) {
            System.out.println("While loop iteration #" + i);
            i++;
        }

        // do-while loop
        int j = 1;
        do {
            System.out.println("Do-while loop iteration #" + j);
            j++;
        } while (j <= 5);

        // for loop
        for (int k = 1; k <= 5; k++) {
            System.out.println("For loop iteration #" + k);
        }

        // break statement
        for (int l = 1; l <= 10; l++) {
            if (l == 5) {
                System.out.println("Break statement encountered.");
                break;
            }
            System.out.println("For loop iteration #" + l);
        }

        input.close();
    }
}

