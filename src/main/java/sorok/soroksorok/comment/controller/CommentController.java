package sorok.soroksorok.comment.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import sorok.soroksorok.comment.dto.CommentEditReq;
import sorok.soroksorok.comment.dto.CommentReq;
import sorok.soroksorok.comment.dto.CommentRes;
import sorok.soroksorok.comment.service.CommentService;
import sorok.soroksorok.global.login.UserDetailsImpl;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "Comment API - 댓글 기능 API")
@Slf4j
@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

  private final CommentService commentService;

  @ApiOperation(
      value = "댓글 작성")
  @ApiResponses({
      @ApiResponse(responseCode = "201", description = "댓글 작성 성공") })
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
  public void createComment(
      @RequestParam Long postId,
      @RequestPart CommentReq req,
      @ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails
  ) {
    commentService.createComment(postId, req, userDetails.getUser());
  }

  @ApiOperation(
      value = "댓글 페이징 조회")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "댓글 페이징 조회 성공") })
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Page<CommentRes> selectComment(
      @RequestParam Long postId,
      @RequestParam Integer page
  ) {
    return commentService.selectComment(postId, page);
  }

  @ApiOperation(
      value = "댓글 수정")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "댓글 수정 성공") })
  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void editComment(
      @PathVariable Long id,
      @RequestPart CommentEditReq req,
      @ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails
  ) {
    commentService.editComment(id, req, userDetails.getUser());
  }

  @ApiOperation(
      value = "댓글 삭제")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "댓글 삭제 성공") })
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteComment(
      @PathVariable Long id,
      @ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails
  ) {
    commentService.deleteComment(id, userDetails.getUser());
  }

}
