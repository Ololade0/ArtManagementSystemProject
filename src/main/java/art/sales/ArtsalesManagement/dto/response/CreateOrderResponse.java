package art.sales.ArtsalesManagement.dto.response;

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
