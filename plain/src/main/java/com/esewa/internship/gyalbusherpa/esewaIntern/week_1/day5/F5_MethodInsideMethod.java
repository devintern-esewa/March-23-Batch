package week_1.day5;
class F5_MethodInsideMethod{



    // calculate the sum
    public int add(int a, int b) {

        // calculate sum
        int sum = a + b;
        return sum;
    }

    // calculate the square
    public void square(int num) {
        int result = num * num;
        System.out.println(result);    // prints 576
    }
    public static void main(String[] args) {

        F5_MethodInsideMethod obj = new F5_MethodInsideMethod();

        // call the square() method
        // passing add() as an argument
        obj.square(obj.add(15, 9));
    }
}
