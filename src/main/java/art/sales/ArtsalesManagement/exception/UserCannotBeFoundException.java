package art.sales.ArtsalesManagement.exception;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserCannotBeFoundException extends RuntimeException {
    private int statusCode;
    public UserCannotBeFoundException(String message) {
        super(message);
    }



    public UserCannotBeFoundException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;

    }

    public static String UserCannotBeFoundException(long id) {
        return "User with ID " + id + " cannot be found";
    }
    public static String UserCannotBeFoundException(String email) {

        return "User with email " + email + " cannot be found";
    }


}
