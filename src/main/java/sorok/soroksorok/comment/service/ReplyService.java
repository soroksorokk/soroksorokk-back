package sorok.soroksorok.comment.service;

import org.springframework.data.domain.Page;
import sorok.soroksorok.comment.dto.ReplyEditReq;
import sorok.soroksorok.comment.dto.ReplyReq;
import sorok.soroksorok.comment.dto.ReplyRes;
import sorok.soroksorok.comment.entity.Reply;
import sorok.soroksorok.user.entity.User;

public interface ReplyService {

  void createReply(Long commentId, ReplyReq req, User user);

  Page<ReplyRes> selectReplies(Long commentId, Integer page);

  void editReply(Long replyId, ReplyEditReq req, User user);

  void deleteReply(Long replyId, User user);

  Reply getReplyEntityById(Long replyId);

  void likeReply(Reply reply, User user);

  void unlikeReply(Reply reply, User user);
}
