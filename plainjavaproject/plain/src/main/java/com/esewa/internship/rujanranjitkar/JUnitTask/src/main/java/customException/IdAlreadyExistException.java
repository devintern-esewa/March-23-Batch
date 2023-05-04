package customException;

public class IdAlreadyExistException extends RuntimeException{
    public IdAlreadyExistException(String str){
        super(str);
    }
}
