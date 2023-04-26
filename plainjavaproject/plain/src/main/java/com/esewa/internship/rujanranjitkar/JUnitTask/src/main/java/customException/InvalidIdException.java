package customException;

public class InvalidIdException extends RuntimeException{
    public InvalidIdException(String str){
        super(str);
    }
}
