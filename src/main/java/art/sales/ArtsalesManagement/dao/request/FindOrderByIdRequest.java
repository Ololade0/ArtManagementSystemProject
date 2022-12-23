package art.sales.ArtsalesManagement.dao.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindOrderByIdRequest {
    private Long userId;
    private Long orderId;
}
