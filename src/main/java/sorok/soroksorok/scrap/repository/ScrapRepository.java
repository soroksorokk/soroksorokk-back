package sorok.soroksorok.scrap.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import sorok.soroksorok.scrap.entity.Scrap;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {

  long countByFeed_Id(Long id);

  Optional<Scrap> findByFeed_IdAndUser_Id(Long id, Long id1);

}
