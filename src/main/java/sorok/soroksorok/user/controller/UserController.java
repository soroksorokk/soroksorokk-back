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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/api/users")
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
      value = "내 정보 수정(이미지)")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "내 정보 수정(이미지) 성공") })
  @PutMapping("/image")
  @ResponseStatus(HttpStatus.OK)
  public void editMyProfileImage(
      @RequestPart MultipartFile image,
      @ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails
  ) throws IOException {
    userService.editMyProfileImage(image, userDetails.getUser());
  }

  @ApiOperation(
      value = "내 정보 수정(설명)")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "내 정보 수정(설명) 성공") })
  @PutMapping("/description")
  @ResponseStatus(HttpStatus.OK)
  public void editMyProfileDescription(
      @RequestBody UserProfileEditReq req,
      @ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails
  ) {
    userService.editMyProfileDescription(req, userDetails.getUser());
  }

  @ApiOperation(
      value = "사용자 정보 조회")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "내 정보 수정(설명) 성공") })
  @GetMapping("/{nickname}")
  @ResponseStatus(HttpStatus.OK)
  public UserProfileRes editMyProfileDescription(
      @PathVariable String nickname
  ) {
    return userService.selectUserProfileByNickname(nickname);
  }


}
