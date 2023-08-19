package sorok.soroksorok.comment.service;

import org.springframework.data.domain.Page;
import sorok.soroksorok.comment.dto.CommentEditReq;
import sorok.soroksorok.comment.dto.CommentReq;
import sorok.soroksorok.comment.dto.CommentRes;
import sorok.soroksorok.user.entity.User;

public interface ReplyService {

  void createReply(Long commentId, CommentReq req, User user);

  Page<CommentRes> selectReplies(Long commentId, Integer page);

  void editReply(Long replyId, CommentEditReq req, User user);

  void deleteReply(Long replyId, User user);

}
