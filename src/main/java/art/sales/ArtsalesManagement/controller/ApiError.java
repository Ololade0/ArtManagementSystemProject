package art.sales.ArtsalesManagement.controller;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiError {
    private String message;
    private int statusCode;
    private boolean successful;
}
