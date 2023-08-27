package sorok.soroksorok.like.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sorok.soroksorok.comment.entity.Comment;
import sorok.soroksorok.comment.entity.Reply;
import sorok.soroksorok.comment.service.CommentService;
import sorok.soroksorok.comment.service.ReplyService;
import sorok.soroksorok.feed.entity.Feed;
import sorok.soroksorok.feed.service.FeedService;
import sorok.soroksorok.global.login.UserDetailsImpl;
import sorok.soroksorok.like.service.LikeService;
import springfox.documentation.annotations.ApiIgnore;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LikeController {

  private final LikeService likeService;
  private final FeedService feedService;
  private final ReplyService replyService;
  private final CommentService commentService;

  @Transactional
  @PostMapping("/feeds/{feedId}/likes")
  public void likeFeed(
      @PathVariable Long feedId,
      @ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails
  ) {
    Feed feed = feedService.getFeedEntityById(feedId);
    likeService.likeFeed(feed, userDetails.getUser());
  }

  @Transactional
  @DeleteMapping("/feeds/{feedId}/likes")
  public void unlikeFeed(
      @PathVariable Long feedId,
      @ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails
  ) {
    Feed feed = feedService.getFeedEntityById(feedId);
    likeService.unlikeFeed(feed, userDetails.getUser());
  }

  @Transactional
  @PostMapping("/comments/{commentId}/likes")
  public void likeComment(
      @PathVariable Long commentId,
      @ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails
  ) {
    Comment comment = commentService.getCommentEntityById(commentId);
    likeService.likeComment(comment, userDetails.getUser());
  }

  @Transactional
  @DeleteMapping("/comments/{commentId}/likes")
  public void unlikeComment(
      @PathVariable Long commentId,
      @ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails
  ) {
    Comment comment = commentService.getCommentEntityById(commentId);
    likeService.unlikeComment(comment, userDetails.getUser());
  }

  @Transactional
  @PostMapping("/replies/{replyId}/likes")
  public void likeReply(
      @PathVariable Long replyId,
      @ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails
  ) {
    Reply reply = replyService.getReplyEntityById(replyId);
    replyService.likeReply(reply, userDetails.getUser());
  }

  @Transactional
  @DeleteMapping("/replies/{replyId}/likes")
  public void unlikeReply(
      @PathVariable Long replyId,
      @ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails
  ) {
    Reply reply = replyService.getReplyEntityById(replyId);
    replyService.unlikeReply(reply, userDetails.getUser());
  }

  @Transactional
  @GetMapping("/feeds/{feedId}/likes")
  public Long selectFeedLikeCount(
      @PathVariable Long feedId
  ) {
    Feed feed = feedService.getFeedEntityById(feedId);
    return likeService.selectFeedLikeCount(feed);
  }

  @Transactional
  @GetMapping("/comments/{commentId}/likes")
  public Long selectCommentLike(
      @PathVariable Long commentId
  ) {
    Comment comment = commentService.getCommentEntityById(commentId);
    return likeService.selectCommentLikeCount(comment);
  }

  @Transactional
  @GetMapping("/replies/{replyId}/likes")
  public Long selectReplyLike(
      @PathVariable Long replyId
  ) {
    Reply reply = replyService.getReplyEntityById(replyId);
    return likeService.selectReplyLikeCount(reply);
  }

}
