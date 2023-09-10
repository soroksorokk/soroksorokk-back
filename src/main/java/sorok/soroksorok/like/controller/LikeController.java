package sorok.soroksorok.like.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

@Api(tags = "Like API - 좋아요 기능 API")
@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LikeController {

  private final LikeService likeService;
  private final FeedService feedService;
  private final ReplyService replyService;
  private final CommentService commentService;

  @ApiOperation(
      value = "게시글 좋아요")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "좋아요 성공") })
  @Transactional
  @PostMapping("/feeds/{feedId}/likes")
  public void likeFeed(
      @PathVariable Long feedId,
      @ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails
  ) {
    Feed feed = feedService.getFeedEntityById(feedId);
    likeService.likeFeed(feed, userDetails.getUser());
  }

  @ApiOperation(
      value = "게시글 좋아요 취소")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "좋아요 취소 성공") })
  @Transactional
  @DeleteMapping("/feeds/{feedId}/likes")
  public void unlikeFeed(
      @PathVariable Long feedId,
      @ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails
  ) {
    Feed feed = feedService.getFeedEntityById(feedId);
    likeService.unlikeFeed(feed, userDetails.getUser());
  }

  @ApiOperation(
      value = "댓글 좋아요")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "댓글 좋아요 성공") })
  @Transactional
  @PostMapping("/comments/{commentId}/likes")
  public void likeComment(
      @PathVariable Long commentId,
      @ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails
  ) {
    Comment comment = commentService.getCommentEntityById(commentId);
    likeService.likeComment(comment, userDetails.getUser());
  }

  @ApiOperation(
      value = "댓글 좋아요 취소")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "댓글 좋아요 취소 성공") })
  @Transactional
  @DeleteMapping("/comments/{commentId}/likes")
  public void unlikeComment(
      @PathVariable Long commentId,
      @ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails
  ) {
    Comment comment = commentService.getCommentEntityById(commentId);
    likeService.unlikeComment(comment, userDetails.getUser());
  }

  @ApiOperation(
      value = "답글 좋아요")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "답글 좋아요 성공") })
  @Transactional
  @PostMapping("/replies/{replyId}/likes")
  public void likeReply(
      @PathVariable Long replyId,
      @ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails
  ) {
    Reply reply = replyService.getReplyEntityById(replyId);
    likeService.likeReply(reply, userDetails.getUser());
  }

  @ApiOperation(
      value = "답글 좋아요 취소")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "답글 좋아요 취소 성공") })
  @Transactional
  @DeleteMapping("/replies/{replyId}/likes")
  public void unlikeReply(
      @PathVariable Long replyId,
      @ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails
  ) {
    Reply reply = replyService.getReplyEntityById(replyId);
    likeService.unlikeReply(reply, userDetails.getUser());
  }

  @ApiOperation(
      value = "게시글 좋아요 갯수 조회")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "게시글 좋아요 갯수 조회 성공") })
  @Transactional
  @GetMapping("/feeds/{feedId}/likes")
  public Long selectFeedLikeCount(
      @PathVariable Long feedId
  ) {
    Feed feed = feedService.getFeedEntityById(feedId);
    return likeService.selectFeedLikeCount(feed);
  }

  @ApiOperation(
      value = "댓글 좋아요 갯수 조회")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "댓글 좋아요 갯수 조회 성공") })
  @Transactional
  @GetMapping("/comments/{commentId}/likes")
  public Long selectCommentLike(
      @PathVariable Long commentId
  ) {
    Comment comment = commentService.getCommentEntityById(commentId);
    return likeService.selectCommentLikeCount(comment);
  }

  @ApiOperation(
      value = "대댓글 좋아요 갯수 조회")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "대댓글 좋아요 갯수 조회 성공") })
  @Transactional
  @GetMapping("/replies/{replyId}/likes")
  public Long selectReplyLike(
      @PathVariable Long replyId
  ) {
    Reply reply = replyService.getReplyEntityById(replyId);
    return likeService.selectReplyLikeCount(reply);
  }

}
