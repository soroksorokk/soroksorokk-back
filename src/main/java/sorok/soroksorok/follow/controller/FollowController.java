package sorok.soroksorok.follow.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import sorok.soroksorok.follow.dto.FollowRes;
import sorok.soroksorok.follow.service.FollowService;
import sorok.soroksorok.global.login.UserDetailsImpl;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "Follow API - 팔로우 기능 API")
@RestController
@RequestMapping("/api/follows")
@RequiredArgsConstructor
public class FollowController {

  private final FollowService followService;

  @ApiOperation(
      value = "유저 팔로우")
  @ApiResponses({
      @ApiResponse(responseCode = "201", description = "팔로우 성공") })
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("/{nickname}")
  public void followUser(
      @PathVariable String nickname,
      @ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails
  ) {
    followService.followUser(userDetails.getUser(), nickname);
  }

  @ApiOperation(
      value = "유저 언팔로우")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "언팔로우 성공") })
  @ResponseStatus(HttpStatus.OK)
  @DeleteMapping("/{nickname}")
  public void unfollowUser(
      @PathVariable String nickname,
      @ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails
  ) {
    followService.unfollowUser(userDetails.getUser(), nickname);
  }

  @ApiOperation(
      value = "유저 팔로우 목록 조회")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "팔로우 목록 조회 성공") })
  @GetMapping("/followings")
  @ResponseStatus(HttpStatus.OK)
  public List<FollowRes> selectMyFollowingUsers(
      @RequestParam String nickname,
      @ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails
  ) {
    return followService.selectMyFollowingUsers(userDetails.getUser());
  }

  @ApiOperation(
      value = "유저 팔로워 목록 조회")
  @ApiResponses({
      @ApiResponse(responseCode = "201", description = "팔로워 목록 조회 성공") })
  @GetMapping("/followers")
  @ResponseStatus(HttpStatus.OK)
  public List<FollowRes> selectMyFollowerUsers(
      @RequestParam String nickname,
      @ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails
  ) {
    return followService.selectMyFollowerUsers(userDetails.getUser());
  }


}
