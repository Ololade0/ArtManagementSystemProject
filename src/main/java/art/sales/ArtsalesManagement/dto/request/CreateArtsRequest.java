package art.sales.ArtsalesManagement.dto.request;

import art.sales.ArtsalesManagement.dao.model.enumPackage.ArtType;
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
