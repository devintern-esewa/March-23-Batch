package week_2.day9.assignments;

class MyCheckedException extends Exception{
    MyCheckedException(){
        super("Age cannot be under 18");
    }
    MyCheckedException(String message){
        super(message);
    }
}

class MyRunTimeException extends RuntimeException{
    MyRunTimeException(){
        super("Age cannot be under 18");
    }
    MyRunTimeException(String message){
        super(message);
    }
}
public class CustomException {
    public static void main(String[] args){
        int age = 2;
//        try {
//            if (age < 18) {
////                throw new CheckedException();
//                throw new MyCheckedException("age below 18 is not ok");
//            }
//        }catch(MyCheckedException e){
//            System.out.println(e);
//        }
//        System.out.println("h");
        try{
            if(age<18){
                throw new MyRunTimeException("i am don");
            }
        }
        catch (MyRunTimeException e){
            e.printStackTrace();
        }
        System.out.println("don");
    }
}
