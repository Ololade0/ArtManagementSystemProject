package art.sales.ArtsalesManagement.dto.request;

import art.sales.ArtsalesManagement.dao.model.enumPackage.RoleType;
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
