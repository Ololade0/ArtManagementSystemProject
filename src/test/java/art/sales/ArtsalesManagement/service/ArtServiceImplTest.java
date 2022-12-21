package art.sales.ArtsalesManagement.service;
import art.sales.ArtsalesManagement.dao.request.CreateArtsRequest;
import art.sales.ArtsalesManagement.dao.request.FindAllArtRequest;
import art.sales.ArtsalesManagement.dao.request.UpdateArtRequest;
import art.sales.ArtsalesManagement.dto.model.Art;
import art.sales.ArtsalesManagement.dto.model.enumPackage.ArtType;
import art.sales.ArtsalesManagement.exception.ArtCannotBeFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest

class ArtServiceImplTest {
    Art createdArt;
    @Autowired
    private ArtService artService;

    @BeforeEach
    void setUp() {
        CreateArtsRequest createArtRequest = CreateArtsRequest.builder()
             .artTitle("Design")
            .artDescription("Art Description")
            .artCategory("Traditional category")
             .artLabel("Red Shape")
             .artPrice(BigDecimal.valueOf(40000))
              .artType(ArtType.ARCHITECTURE)
                .build();
      createdArt =  artService.createArt(createArtRequest);

    }

    @AfterEach
    void tearDown() {
        artService.deleteAllArt();

    }
    @Test
    public void testThatArtCanBeCreated(){
        CreateArtsRequest createArtRequest = CreateArtsRequest.builder()
                .artTitle("Design")
                .artDescription("Art Description")
                .artCategory("Traditional category")
                .artLabel("Red Shape")
                .artPrice(BigDecimal.valueOf(40000))
                .artType(ArtType.ARCHITECTURE)
                .build();
      Art createdArt =  artService.createArt(createArtRequest);
        assertEquals("Design", createdArt.getArtTitle());
        assertThat(createdArt.getId()).isNotNull();
        assertEquals(2L, artService.totalNoOfArt());
    }

    @Test
    public void findArtById() throws ArtCannotBeFoundException {
      Art foundArt =  artService.findArtById(createdArt.getId());
      assertThat(foundArt.getId()).isEqualTo(createdArt.getId());
      assertThat(foundArt.getId()).isNotNull();
    }


    @Test
    public void findAllArtById() throws ArtCannotBeFoundException {
        FindAllArtRequest findAllArtRequest = FindAllArtRequest.builder()
        .numberOfPages(2)
         .pages(1)
                .artId(createdArt.getId())
                .build();
     Page<Art> foundArt =   artService.findAllArt(findAllArtRequest);
        assertThat(foundArt.getTotalElements()).isNotNull();
    }

    @Test
    public void deleteArtById() {
      String delete =  artService.deleteArt(createdArt.getId());
      assertThat(delete).isNotNull();
      assertEquals(0, artService.totalNoOfArt());
    }

    @Test
    public void deleteAllArt() {
        artService.deleteAllArt();
        assertEquals(0, artService.totalNoOfArt());
    }

    @Test
    public void updateArtProfile() throws ArtCannotBeFoundException {
        UpdateArtRequest updateArtRequest = UpdateArtRequest
                .builder()
                .artId(createdArt.getId())
                .artCategory("Modern Category")
                .artDescription("Modern Description")
                .artLabel("Diamond Art")
                .artPrice(BigDecimal.valueOf(80_000))
                .artTitle("Painting")
                .artType(ArtType.PAINTING)
                .build();
    Art updatedArt =    artService.updateArtProfile(updateArtRequest);
        assertEquals("Diamond Art", updatedArt.getArtLabel());
        assertEquals("Painting", updatedArt.getArtTitle());
        assertEquals("Modern Category", updatedArt.getArtCategory());
        assertEquals("Modern Description", updatedArt.getArtDescription());
        assertEquals(BigDecimal.valueOf(80_000), updatedArt.getArtPrice());
        assertEquals(ArtType.PAINTING, updatedArt.getArtType());
    }






}