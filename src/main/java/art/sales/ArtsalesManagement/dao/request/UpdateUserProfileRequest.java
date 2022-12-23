package art.sales.ArtsalesManagement.dao.request;

import art.sales.ArtsalesManagement.dto.model.enumPackage.RoleType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserProfileRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNo;
    private String password;
    private String address;
    private RoleType roleType;
}
