package sorok.soroksorok.comment.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sorok.soroksorok.comment.dto.ReplyEditReq;
import sorok.soroksorok.comment.dto.ReplyReq;
import sorok.soroksorok.comment.dto.ReplyRes;
import sorok.soroksorok.comment.entity.Comment;
import sorok.soroksorok.comment.entity.Reply;
import sorok.soroksorok.comment.repository.ReplyRepository;
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
    Reply reply = req.toEntity(comment, req, user);
    replyRepository.save(reply);
  }

  @Override
  @Transactional
  public Page<ReplyRes> selectReplies(Long commentId, Integer page) {
    PageRequest pageable = PageRequest.of(page, 10);

    long total = replyRepository.countByParentComment_Id(commentId);
    Page<Reply> replies = replyRepository.findByParentComment_IdOrderByCreatedAtDesc(
        commentId, pageable);

    List<ReplyRes> commentResList = ReplyRes.of(replies);
    return new PageImpl<>(commentResList, pageable, total);
  }

  @Override
  @Transactional
  public void editReply(Long replyId, ReplyEditReq req, User user) {
    Reply reply = replyRepository.findById(replyId).orElseThrow(RuntimeException::new);
    validateUser(user, reply);
    reply.editComment(req);
  }

  @Override
  @Transactional
  public void deleteReply(Long replyId, User user) {
    Reply reply = replyRepository.findById(replyId).orElseThrow(RuntimeException::new);
    validateUser(user, reply);
    replyRepository.delete(reply);
  }

  @Override
  @Transactional
  public Reply getReplyEntityById(Long replyId) {
    return replyRepository.findById(replyId).orElseThrow(RuntimeException::new);
  }

  private void validateUser(User user, Reply reply) {
    if (!reply.isAccessibleUser(user)) {
      throw new RuntimeException();
    }
  }

}
