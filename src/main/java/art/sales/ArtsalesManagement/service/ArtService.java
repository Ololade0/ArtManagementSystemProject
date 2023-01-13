package art.sales.ArtsalesManagement.service;

import art.sales.ArtsalesManagement.dao.model.Art;
import art.sales.ArtsalesManagement.dto.request.CreateArtsRequest;
import art.sales.ArtsalesManagement.dto.request.FindAllArtRequest;
import art.sales.ArtsalesManagement.dto.request.UpdateArtRequest;
import art.sales.ArtsalesManagement.exception.ArtCannotBeFoundException;
import org.springframework.data.domain.Page;

public interface ArtService {
    Art createArt(CreateArtsRequest createArtRequest);

    long totalNoOfArt();

    String deleteAllArt();

    Art findArtById(Long id) throws ArtCannotBeFoundException;

    Page<Art> findAllArt(FindAllArtRequest findAllArtRequest);

    String deleteArt(Long id);

    Art updateArtProfile(UpdateArtRequest updateArtRequest) throws ArtCannotBeFoundException;
}
