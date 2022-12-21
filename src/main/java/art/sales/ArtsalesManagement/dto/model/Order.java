package art.sales.ArtsalesManagement.dto.model;

import art.sales.ArtsalesManagement.dto.model.enumPackage.PaymentType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name="order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String address;
    private LocalDateTime ordered_at;
    private LocalDateTime paymentTime;
    private String paymentDescription;
    @Enumerated
    private PaymentType paymentType;

}
