package sorok.soroksorok.comment.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sorok.soroksorok.comment.dto.CommentEditReq;
import sorok.soroksorok.comment.dto.CommentReq;
import sorok.soroksorok.comment.dto.CommentRes;
import sorok.soroksorok.comment.entity.Comment;
import sorok.soroksorok.comment.repository.CommentRepository;
import sorok.soroksorok.feed.entity.Feed;
import sorok.soroksorok.feed.service.FeedService;
import sorok.soroksorok.user.entity.User;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

  private final CommentRepository commentRepository;
  private final FeedService feedService;

  @Override
  @Transactional
  public void createComment(Long feedId, CommentReq req, User user) {
    Feed feed = feedService.getFeedEntityById(feedId);
    Comment comment = req.toEntity(feed, req, user);
    commentRepository.save(comment);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<CommentRes> selectComment(Long feedId, Integer page) {
    PageRequest pageable = PageRequest.of(page, 10);

    long total = commentRepository.countByFeed_Id(feedId);
    Page<Comment> comments = commentRepository.findByFeed_IdOrderByCreatedAtDesc(
        feedId, pageable);

    List<CommentRes> commentResList = CommentRes.of(comments);
    return new PageImpl<>(commentResList, pageable, total);
  }

  @Override
  @Transactional
  public void editComment(Long id, CommentEditReq req, User user) {
    Comment comment = commentRepository.findById(id).orElseThrow(RuntimeException::new);
    validateUser(user, comment);
    comment.editComment(req);
  }

  @Override
  @Transactional
  public void deleteComment(Long id, User user) {
    Comment comment = commentRepository.findById(id).orElseThrow(RuntimeException::new);
    validateUser(user, comment);
    commentRepository.delete(comment);
  }

  @Override
  @Transactional
  public Comment getCommentEntityById(Long commentId) {
    return commentRepository.findById(commentId).orElseThrow(RuntimeException::new);
  }

  private static void validateUser(User user, Comment comment) {
    if (!comment.isAccessibleUser(user)) {
      throw new RuntimeException();
    }
  }

}
