package art.sales.ArtsalesManagement.service;
import art.sales.ArtsalesManagement.dao.request.CreateArtsRequest;
import art.sales.ArtsalesManagement.dao.request.FindAllArtRequest;
import art.sales.ArtsalesManagement.dao.request.UpdateArtRequest;

import art.sales.ArtsalesManagement.dto.model.Art;

import art.sales.ArtsalesManagement.dto.repository.ArtRepository;
import art.sales.ArtsalesManagement.exception.ArtCannotBeFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArtServiceImpl implements ArtService{
    private final ArtRepository artRepository;
    @Override
    public Art createArt(CreateArtsRequest createArtRequest) {
        ModelMapper modelMapper = new ModelMapper();
        Art  map =   modelMapper.map(createArtRequest, Art.class);
        return artRepository.save(map);

    }

    @Override
    public long totalNoOfArt() {
        return artRepository.count();
    }

    @Override
    public String deleteAllArt() {
        artRepository.deleteAll();
        return "All Art successfully deleted";
    }

    @Override
    public Art findArtById(Long id) throws ArtCannotBeFoundException {
        Optional<Art> foundArt= artRepository.findById(id);
        if(foundArt.isPresent()){
            return foundArt.get();
        }
        else{
            throw new ArtCannotBeFoundException(ArtCannotBeFoundException.ArtCannotBeFoundException(id));
        }
    }

    @Override
    public Page<Art> findAllArt(FindAllArtRequest findAllArtRequest) {
        Pageable pageable = PageRequest.of(findAllArtRequest.getPages()-1, findAllArtRequest.getNumberOfPages());
        return artRepository.findAll(pageable);

    }

    @Override
    public String deleteArt(Long id) {
     Optional<Art>foundArt =   artRepository.findArtById(id);
     if(foundArt.isPresent()){
         artRepository.deleteById(id);
         return  "Art with ID " + foundArt.get().getId() + " successfully deleted ";
     }
        return null;
    }

    @Override
    public Art updateArtProfile(UpdateArtRequest updateArtRequest) throws ArtCannotBeFoundException {
        Optional<Art> foundArt = artRepository.findArtById(updateArtRequest.getArtId());
        if (foundArt.isPresent()) {
            if (updateArtRequest.getArtCategory() != null) {
                foundArt.get().setArtCategory(updateArtRequest.getArtCategory());
            }
            if (updateArtRequest.getArtDescription() != null) {
                foundArt.get().setArtDescription(updateArtRequest.getArtDescription());
            }
            if (updateArtRequest.getArtLabel() != null) {
                foundArt.get().setArtLabel(updateArtRequest.getArtLabel());
            }
            if (updateArtRequest.getArtPrice() != null) {
                foundArt.get().setArtPrice(updateArtRequest.getArtPrice());
            }
            if (updateArtRequest.getArtTitle()!= null) {
                foundArt.get().setArtTitle(updateArtRequest.getArtTitle());
            }
            if (updateArtRequest.getArtType()!= null) {
                foundArt.get().setArtType(updateArtRequest.getArtType());
            }
            return artRepository.save(foundArt.get());
        }
        else {
            throw new ArtCannotBeFoundException(ArtCannotBeFoundException.ArtCannotBeFoundException(updateArtRequest.getArtId()));
        }
    }
}
