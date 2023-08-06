package sorok.soroksorok.comment.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import sorok.soroksorok.comment.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

  long countByFeed_Id(Long id);

  Page<Comment> findByFeed_IdOrderByCreatedAtDesc(Long id, Pageable pageable);

}
