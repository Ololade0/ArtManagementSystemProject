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
@Entity(name ="Role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated
    private RoleType roleType;
}
