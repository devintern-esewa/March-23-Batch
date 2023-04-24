package week_1.day3;

import java.util.Scanner;

public class F4_Question {
    /*
    Operators
    Given the meal price (base cost of a meal), tip percent (the percentage of the mean price being added as tip), and
    tax percent (the percentage of the meal price being added as tax) for a meal,
    find and print the meal's total cost. Round the result to the nearest integer.

    Offer:
    - If the customer orders a meal costing in the range of (5,000-10,000) rupees then give them a flat discount of
    Rs. 500 on the meal price
    - If the customer orders a meal costing more than Rs. 10,000 then give them 20% off on the tip in addition to the
     flat discount of Rs. 700 on the meal price

    Example
    - Meal price = 100
    - Tip percent = 15
    - Tax percent = 8

    Tip is 15% * 100 = 15, and the taxes are 8% * 100 = 8

    Sample Input
    12.00
    20
    8

    Sample Output
    15

    Explanation
    Meal cost = 12.00
    Tip= 20% of 12.00 = 2.4
    Tax = 8% of 12.00 = 0.96
    Total cost = meal cost + tip + tax = 15.36
    round(Total Cost) = 15 // round to nearest integer
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the cost of meal ");
        float cost = sc.nextFloat();

        System.out.println("Enter the tip percent");
        float tip = sc.nextFloat();
        tip = (tip / 100) * cost;

        System.out.println("Enter the tax percent");
        float tax = sc.nextFloat();
        tax = (tax / 100) * cost;


        if (cost >= 5000 && cost <= 10000) {
            cost -= 500;
        } else if (cost > 10000) {
            cost -= 700;
            tip = tip - (((float) 20 / 100) * tip);
        }

        double netAmount = cost + tip + tax;
        System.out.println("Net Amount is " + Math.round(netAmount));
    }
}
