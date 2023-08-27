package sorok.soroksorok.like.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sorok.soroksorok.comment.entity.Reply;
import sorok.soroksorok.like.entity.ReplyLike;

public interface ReplyLikeRepository extends JpaRepository<ReplyLike, Long> {

  long countByReply(Reply reply);

}
