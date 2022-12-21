package art.sales.ArtsalesManagement.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ArtCannotBeFoundException extends Throwable {
    public ArtCannotBeFoundException(String message) {
        super(message);
    }

    public static  String ArtCannotBeFoundException(long artId) {
        return "Art with iD "+ artId + " does not exist";

    }
}
