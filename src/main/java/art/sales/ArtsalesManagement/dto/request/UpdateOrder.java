package art.sales.ArtsalesManagement.dto.request;

import art.sales.ArtsalesManagement.dao.model.enumPackage.PaymentType;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateOrder {
    private Long id;
    private String email;
    private String address;
    private LocalDateTime updatedAt;
     private String paymentDescription;
    private PaymentType paymentType;
}
