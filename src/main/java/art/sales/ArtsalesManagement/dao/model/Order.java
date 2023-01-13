package art.sales.ArtsalesManagement.dao.model;

import art.sales.ArtsalesManagement.dao.model.enumPackage.PaymentType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String address;
    private LocalDateTime ordered_at;
    private LocalDateTime paymentTime;
    private String paymentDescription;

////    @ManyToOne
////    @JoinColumn(name = "user_id")
//    private User user;

    @Enumerated
    private PaymentType paymentType;

}
