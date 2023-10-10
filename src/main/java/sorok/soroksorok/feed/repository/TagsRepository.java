package sorok.soroksorok.feed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sorok.soroksorok.feed.entity.Feed;
import sorok.soroksorok.feed.entity.Tags;

public interface TagsRepository extends JpaRepository<Tags, Long> {

  long deleteByFeedAllIgnoreCase(
      Feed feed);

}
