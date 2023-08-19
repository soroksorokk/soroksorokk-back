package sorok.soroksorok.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sorok.soroksorok.comment.entity.Reply;

public interface ReplyRepository extends JpaRepository<Long, Reply> {

}
