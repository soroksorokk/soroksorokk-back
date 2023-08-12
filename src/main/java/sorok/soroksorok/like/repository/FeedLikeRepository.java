package sorok.soroksorok.like.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import sorok.soroksorok.feed.entity.Feed;
import sorok.soroksorok.like.entity.FeedLike;
import sorok.soroksorok.user.entity.User;

public interface FeedLikeRepository extends JpaRepository<FeedLike, Long> {

  boolean existsByUserAndFeed(
      User user, Feed feed);

  Optional<FeedLike> findByUserAndFeed(User user, Feed feed);

}
