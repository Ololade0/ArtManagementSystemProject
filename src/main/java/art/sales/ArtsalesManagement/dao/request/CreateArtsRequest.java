package art.sales.ArtsalesManagement.dao.request;

import art.sales.ArtsalesManagement.dto.model.enumPackage.ArtType;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateArtsRequest {
    private Long artId;
    private String artTitle;
    private String artDescription;
    private String artCategory;
    private String artLabel;
    private BigDecimal artPrice;
    private ArtType artType;

}
