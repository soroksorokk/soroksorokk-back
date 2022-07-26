package sorok.soroksorok.follow.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import sorok.soroksorok.follow.dto.UserRes;
import sorok.soroksorok.follow.service.FollowService;
import sorok.soroksorok.global.login.UserDetailsImpl;

@RestController
@RequestMapping("/api/follows")
@RequiredArgsConstructor
public class FollowController {

  private final FollowService followService;

  @ResponseStatus(HttpStatus.OK)
  @PostMapping("/{nickname}")
  public void followUser(
      @PathVariable String nickname,
      @AuthenticationPrincipal UserDetailsImpl userDetails
  ) {
    followService.followUser(userDetails.getUser(), nickname);
  }

  @ResponseStatus(HttpStatus.OK)
  @DeleteMapping("/{nickname}")
  public void unfollowUser(
      @PathVariable String nickname,
      @AuthenticationPrincipal UserDetailsImpl userDetails
  ) {
    followService.unfollowUser(userDetails.getUser(), nickname);
  }

  @GetMapping("/following")
  @ResponseStatus(HttpStatus.OK)
  public List<UserRes> selectMyFollowingUsers(
      @AuthenticationPrincipal UserDetailsImpl userDetails
  ) {
    return followService.selectMyFollowingUsers(userDetails.getUser());
  }

  @GetMapping("/follower")
  @ResponseStatus(HttpStatus.OK)
  public List<UserRes> selectMyFollowerUsers(
      @AuthenticationPrincipal UserDetailsImpl userDetails
  ) {
    return followService.selectMyFollowerUsers(userDetails.getUser());
  }


}
