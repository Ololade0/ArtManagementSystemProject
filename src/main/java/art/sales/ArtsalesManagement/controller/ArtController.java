package art.sales.ArtsalesManagement.controller;

import art.sales.ArtsalesManagement.dao.model.Art;
import art.sales.ArtsalesManagement.dto.request.CreateArtsRequest;
import art.sales.ArtsalesManagement.dto.request.FindAllArtRequest;
import art.sales.ArtsalesManagement.dto.request.UpdateArtRequest;
import art.sales.ArtsalesManagement.exception.ArtCannotBeFoundException;
import art.sales.ArtsalesManagement.service.ArtService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/art")
@RequiredArgsConstructor
public class ArtController {
    private final ArtService artService;


    @PostMapping("/art")
    public ResponseEntity<?> createArt(CreateArtsRequest createArtsRequest){
       Art art  = artService.createArt(createArtsRequest);
        return new ResponseEntity<>(art, HttpStatus.CREATED);
    }


    @GetMapping("/{artId}")
    public ResponseEntity<?> findArtById(@PathVariable Long artId) throws ArtCannotBeFoundException {
        Art foundArt  = artService.findArtById(artId);
        return new ResponseEntity<>(foundArt, HttpStatus.CREATED);
    }


    @GetMapping("/findAllArt")
    public ResponseEntity<?> findAllArt(FindAllArtRequest findAllArtRequest){
        Page<Art> foundArt  = artService.findAllArt(findAllArtRequest);
        return new ResponseEntity<>(foundArt, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArt(@PathVariable Long artId){
        String deletedArt  = artService.deleteArt(artId);
        return new ResponseEntity<>(deletedArt, HttpStatus.CREATED);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteAll(){
        String deletedArt  = artService.deleteAllArt();
        return new ResponseEntity<>(deletedArt, HttpStatus.CREATED);
    }



    @PutMapping()
    public ResponseEntity<?> updateArt(@RequestBody UpdateArtRequest updateArtRequest) throws ArtCannotBeFoundException {
       Art updatedArt  = artService.updateArtProfile(updateArtRequest);
        return new ResponseEntity<>(updatedArt, HttpStatus.CREATED);
    }

}
