package art.sales.ArtsalesManagement.dao.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderResponse {
    private Long orderId;
    private String message;
}
