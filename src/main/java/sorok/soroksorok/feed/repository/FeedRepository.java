package sorok.soroksorok.feed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sorok.soroksorok.feed.entity.Feed;

public interface FeedRepository extends JpaRepository<Feed, Long> {

}
