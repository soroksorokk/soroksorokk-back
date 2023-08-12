package sorok.soroksorok.like.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import sorok.soroksorok.comment.entity.Comment;
import sorok.soroksorok.like.entity.CommentLike;
import sorok.soroksorok.user.entity.User;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {

  boolean existsByUserAndComment(
      User user, Comment comment);

  Optional<CommentLike> findByUserAndComment(User user, Comment comment);

}
