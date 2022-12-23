package art.sales.ArtsalesManagement.dao.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindAllOrderRequest {
    private int numberOfPages;
    private int pages;
    private Long orderId;
    private Long userId;
}
