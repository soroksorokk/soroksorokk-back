package sorok.soroksorok.comment.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sorok.soroksorok.comment.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

  Page<Reply> findByParentComment_IdOrderByCreatedAtDesc(Long id, Pageable pageable);

  long countByParentComment_Id(
      Long id);

}
