package sorok.soroksorok.like.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import sorok.soroksorok.comment.entity.Reply;
import sorok.soroksorok.like.entity.ReplyLike;
import sorok.soroksorok.user.entity.User;

public interface ReplyLikeRepository extends JpaRepository<ReplyLike, Long> {

  Optional<ReplyLike> findByUserAndReply(User user, Reply reply);

  boolean existsByUserAndReply(
      User user, Reply reply);

  long countByReply(Reply reply);

}
