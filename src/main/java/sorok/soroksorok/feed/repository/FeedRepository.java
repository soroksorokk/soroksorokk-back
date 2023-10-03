package sorok.soroksorok.feed.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sorok.soroksorok.feed.entity.Feed;
import sorok.soroksorok.feed.entity.Mood;

public interface FeedRepository extends JpaRepository<Feed, Long> {

  Page<Feed> findByIdGreaterThanEqualOrderByCreatedAtDesc(Long id, Pageable pageable);

  Page<Feed> findByMoodOrderByCreatedAtDesc(Mood mood, Pageable pageable);

  List<Feed> findByUser_NicknameOrderByCreatedAtDesc(String nickname);

}
