package sorok.soroksorok.comment.service;

import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import sorok.soroksorok.comment.dto.CommentEditReq;
import sorok.soroksorok.comment.dto.CommentReq;
import sorok.soroksorok.comment.dto.CommentRes;
import sorok.soroksorok.user.entity.User;

public interface CommentService {

  void createComment(Long postId, CommentReq req, User user);

  @Transactional(readOnly = true)
  Page<CommentRes> selectComment(Long feedId, Integer page);

  void editComment(Long id, CommentEditReq req, User user);

  void deleteComment(Long id, User user);
}
