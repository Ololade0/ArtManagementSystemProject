package art.sales.ArtsalesManagement.dao.repository;

import art.sales.ArtsalesManagement.dao.model.Art;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ArtRepository extends JpaRepository<Art, Long> {
    Optional<Art> findArtById(long artId);
}
