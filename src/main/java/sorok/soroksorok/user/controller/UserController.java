package sorok.soroksorok.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
import sorok.soroksorok.user.dto.UserProfileEditReq;
import sorok.soroksorok.user.dto.UserProfileRes;
import sorok.soroksorok.user.service.UserService;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "User API - 유저 기능 API")
@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @ApiOperation(
      value = "내 정보 조회")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "내 정보 조회 성공") })
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public UserProfileRes selectMyProfile(
      @ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails
  ) {
    return userService.selectMyProfile(userDetails.getUser());
  }

  @ApiOperation(
      value = "내 정보 수정")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "내 정보 수정 성곤") })
  @PutMapping
  @ResponseStatus(HttpStatus.OK)
  public void editMyProfile(
      @RequestPart MultipartFile image,
      @RequestPart UserProfileEditReq req,
      @ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails
  ) throws IOException {
    userService.editMyProfile(image, req, userDetails.getUser());
  }

}
