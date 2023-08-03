package sorok.soroksorok.auth.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sorok.soroksorok.auth.dto.SignUpRequest;
import sorok.soroksorok.auth.service.AuthService;

@Api(tags = "Auth API - 인증/인가")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @ApiOperation(
      value = "회원가입")
  @ApiResponses({
      @ApiResponse(responseCode = "201", description = "회원가입 성공") })
  @PostMapping("/sign-up")
  @ResponseStatus(HttpStatus.CREATED)
  public void signUp(
      @RequestPart(required = false) MultipartFile image,
      @RequestPart SignUpRequest signUpRequest
  ) throws Exception {
    authService.signUp(signUpRequest, image);
  }

  @ApiOperation(
      value = "로그인")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "로그인 성공") })
  @PostMapping("/sign-in")
  public void signIn() {
  }

  @ApiOperation(
      value = "토큰 재발급")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "토큰 재발급 성공") })
  @PostMapping("/re-issue")
  public void reIssue() {
  }

}