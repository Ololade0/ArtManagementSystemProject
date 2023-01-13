package art.sales.ArtsalesManagement.dao.model;

import art.sales.ArtsalesManagement.dao.model.enumPackage.RoleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated
    private RoleType roleType;

       public Role(RoleType roleType) {
            this.roleType = roleType;
        }


}
