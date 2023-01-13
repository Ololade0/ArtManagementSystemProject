package art.sales.ArtsalesManagement.dao.model;

import art.sales.ArtsalesManagement.dao.model.enumPackage.ArtType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name = "Art")
public class Art {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String artTitle;
    private String artDescription;
    private String artCategory;
    private String artLabel;
    private BigDecimal artPrice;
    @Enumerated
    private ArtType artType;
}
