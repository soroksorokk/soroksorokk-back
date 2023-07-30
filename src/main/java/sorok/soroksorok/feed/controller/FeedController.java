package sorok.soroksorok.feed.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sorok.soroksorok.feed.dto.FeedEditReq;
import sorok.soroksorok.feed.dto.FeedReq;
import sorok.soroksorok.feed.dto.FeedRes;
import sorok.soroksorok.feed.dto.FeedSearchCond;
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
      value = "게시글 페이징 조회")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "게시글 페이징 조회 성공")})
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public void selectFeeds(
      @RequestParam FeedSearchCond cond
  ) {

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
  ) {

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

  }

}
