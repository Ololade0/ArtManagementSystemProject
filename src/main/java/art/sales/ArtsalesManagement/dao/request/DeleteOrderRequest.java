package art.sales.ArtsalesManagement.dao.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeleteOrderRequest {
    private  Long userId;
    private Long orderId;
}
