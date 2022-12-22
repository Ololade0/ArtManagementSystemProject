package art.sales.ArtsalesManagement.dao.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindAllOrder {
    private Long orderId;
    private int numberOfPages;
    private int pages;
}
