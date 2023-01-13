package art.sales.ArtsalesManagement.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserResponse {
    private Long userId;
    private String email;
}
