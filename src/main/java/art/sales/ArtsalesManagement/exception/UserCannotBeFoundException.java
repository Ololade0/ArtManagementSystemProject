package art.sales.ArtsalesManagement.exception;

import jakarta.persistence.Id;

public class UserCannotBeFoundException extends RuntimeException {
    public UserCannotBeFoundException(String message) {
        super(message);
    }

    public static String UserCannotBeFoundException(long id) {
        return "User with ID " + id + " cannot be found";
    }
    public static String UserCannotBeFoundException(String email) {

        return "User with email " + email + " cannot be found";
    }
}
