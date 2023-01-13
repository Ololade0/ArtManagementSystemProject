package art.sales.ArtsalesManagement.dto.request;

import art.sales.ArtsalesManagement.dao.model.enumPackage.PaymentType;
import lombok.*;

import java.time.LocalDateTime;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CreateOrderRequest {
        private Long id;
        private String address;
        private PaymentType paymentType;
        private LocalDateTime ordered_at;
        private LocalDateTime paymentTime;
        private String paymentDescription;

}
