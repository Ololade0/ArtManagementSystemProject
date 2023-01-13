package art.sales.ArtsalesManagement.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindAllArtRequest {
    private Long artId;
    private int numberOfPages;
    private int pages;
}
