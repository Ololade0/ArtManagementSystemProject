package art.sales.ArtsalesManagement.dto.model;

import art.sales.ArtsalesManagement.dto.model.enumPackage.RoleType;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNo;
    private String password;
    private String address;
    @Enumerated
    private RoleType roleType;
}
