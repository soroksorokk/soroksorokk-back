package sorok.soroksorok.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sorok.soroksorok.comment.dto.CommentEditReq;
import sorok.soroksorok.comment.dto.CommentReq;
import sorok.soroksorok.comment.dto.CommentRes;
import sorok.soroksorok.comment.dto.ReplyReq;
import sorok.soroksorok.comment.entity.Comment;
import sorok.soroksorok.comment.repository.ReplyRepository;
import sorok.soroksorok.feed.entity.Feed;
import sorok.soroksorok.user.entity.User;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{

  private final ReplyRepository replyRepository;
  private final CommentService commentService;

  @Override
  @Transactional
  public void createReply(Long commentId, ReplyReq req, User user) {
    Comment comment = commentService.getCommentEntityById(commentId);
    req.toEntity(comment, req, user);
    commentRepository.save(comment);
  }

  @Override
  public Page<CommentRes> selectReplies(Long commentId, Integer page) {
    return null;
  }

  @Override
  public void editReply(Long replyId, CommentEditReq req, User user) {

  }

  @Override
  public void deleteReply(Long replyId, User user) {

  }
}
