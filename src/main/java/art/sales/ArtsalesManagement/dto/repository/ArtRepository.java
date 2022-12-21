package art.sales.ArtsalesManagement.dto.repository;

import art.sales.ArtsalesManagement.dto.model.Art;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ArtRepository extends JpaRepository<Art, Long> {
    Optional<Art> findArtById(long artId);
}
