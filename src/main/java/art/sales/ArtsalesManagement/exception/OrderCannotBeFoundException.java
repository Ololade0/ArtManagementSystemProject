package art.sales.ArtsalesManagement.exception;

public class OrderCannotBeFoundException extends RuntimeException {
    public OrderCannotBeFoundException(String message){
        super(message);
    }
    public static String OrderCannotBeFoundException(long id){
        return "Order with ID" + id + "cannot be found";
    }
}
