package bg.softuni.usermanagementapptest.service.exception;

public class UserNotFoundException extends RuntimeException{


    public UserNotFoundException(String message) {
        super(message);
    }
}
