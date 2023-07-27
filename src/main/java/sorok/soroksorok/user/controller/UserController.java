package sorok.soroksorok.user.controller;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sorok.soroksorok.global.login.UserDetailsImpl;
import sorok.soroksorok.user.dto.UserProfileReq;
import sorok.soroksorok.user.dto.UserProfileRes;
import sorok.soroksorok.user.service.UserService;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public UserProfileRes selectMyProfile(
      @AuthenticationPrincipal UserDetailsImpl userDetails
  ) {
    return userService.selectMyProfile(userDetails.getUser());
  }

  @PutMapping
  @ResponseStatus(HttpStatus.OK)
  public void editMyProfile(
      @RequestPart MultipartFile image,
      @RequestPart UserProfileReq req,
      @AuthenticationPrincipal UserDetailsImpl userDetails
  ) throws IOException {
    userService.editMyProfile(image, req, userDetails.getUser());
  }

}
