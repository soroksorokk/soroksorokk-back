package sorok.soroksorok.feed.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import sorok.soroksorok.feed.entity.TempFeed;
import sorok.soroksorok.user.entity.User;

public interface TempFeedRepository extends JpaRepository<TempFeed, Long> {

  Optional<TempFeed> findByUser(User user);

  void deleteByUser(User user);

}
