package sorok.soroksorok.feed.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.io.IOException;
import java.util.List;
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
import org.springframework.web.multipart.MultipartFile;
import sorok.soroksorok.feed.dto.FeedEditReq;
import sorok.soroksorok.feed.dto.FeedPagingRes;
import sorok.soroksorok.feed.dto.FeedReq;
import sorok.soroksorok.feed.dto.FeedRes;
import sorok.soroksorok.feed.entity.Mood;
import sorok.soroksorok.feed.service.FeedService;
import sorok.soroksorok.global.login.UserDetailsImpl;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "Feed API - 피드 기능 API")
@Slf4j
@RestController
@RequestMapping("/api/feeds")
@RequiredArgsConstructor
public class FeedController {

  private final FeedService feedService;

  @ApiOperation(
      value = "게시글 작성")
  @ApiResponses({
      @ApiResponse(responseCode = "201", description = "게시글 작성 성공") })
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
  public void createFeed(
      @RequestPart(required = false) MultipartFile image,
      @RequestPart FeedReq req,
      @ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails
  ) throws IOException {
    feedService.createFeed(image, req, userDetails.getUser());
  }

  @ApiOperation(
      value = "게시글 상세 조회")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "게시글 상세 조회 성공") })
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public FeedRes selectFeed(
      @PathVariable Long id
  ) {
    return feedService.selectFeed(id);
  }

  @ApiOperation(
      value = "게시글 수정")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "게시글 수정 성공") })
  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void editFeed(
      @PathVariable Long id,
      @RequestPart(required = false) MultipartFile image,
      @RequestPart FeedEditReq req,
      @ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails
  ) throws IOException {
    feedService.editFeed(id, image, req, userDetails.getUser());
  }

  @ApiOperation(
      value = "게시글 삭제")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "게시글 삭제 성공") })
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteFeed(
      @PathVariable Long id,
      @ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails
  ) {
    feedService.deleteFeed(id, userDetails.getUser());
  }

  @ApiOperation(
      value = "사용자가 작성한 게시글 조회")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "게시글 삭제 성공") })
  @GetMapping("/user/{nickname}")
  @ResponseStatus(HttpStatus.OK)
  public List<FeedPagingRes> selectFeedsByNickname(
      @PathVariable String nickname
  ) {
    return feedService.selectFeedsByNickname(nickname);
  }

  @ApiOperation(
      value = "게시글 페이징 조회")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "게시글 조회 성공") })
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Page<FeedPagingRes> selectFeeds(
      @RequestParam(required = false) Mood mood,
      @RequestParam(defaultValue = "1") Integer page
  ) {
    return feedService.selectFeeds(mood, page);
  }


  @ApiOperation(
      value = "게시글 임시 저장")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "게시글 임시 저장 성공") })
  @PostMapping("/temporary")
  @ResponseStatus(HttpStatus.OK)
  public void saveFeedTemporarily(
      @RequestPart(required = false) MultipartFile image,
      @RequestPart FeedReq req,
      @ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails
  ) throws IOException {
    feedService.saveFeedTemporarily(image, req, userDetails.getUser());
  }

  @ApiOperation(
      value = "게시글 임시 저장 불러오기")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "게시글 임시 저장 불러오기") })
  @GetMapping("/temporary")
  @ResponseStatus(HttpStatus.OK)
  public FeedRes getFeedTemporarily(
      @ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails
  ) {
    return feedService.getTemporaryFeed(userDetails.getUser());
  }

}
