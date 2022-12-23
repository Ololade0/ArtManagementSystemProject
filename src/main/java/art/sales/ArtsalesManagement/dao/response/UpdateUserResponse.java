package art.sales.ArtsalesManagement.dao.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserResponse {
    private Long userId;
    private String message;
    private String email;
}
