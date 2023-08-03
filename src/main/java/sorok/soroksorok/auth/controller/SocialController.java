package sorok.soroksorok.auth.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
public class SocialController {

  @ApiOperation(
      value = "구글 소셜 로그인")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "토큰 재발급 성공") })
  @GetMapping("/oauth2/authorization/google")
  public void googleLogin() {
  }

  @ApiOperation(
      value = "네이버 소셜 로그인")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "토큰 재발급 성공") })
  @GetMapping("/oauth2/authorization/naver")
  public void naverLogin() {
  }

  @ApiOperation(
      value = "카카오 소셜 로그인")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "토큰 재발급 성공") })
  @GetMapping("/oauth2/authorization/kakao")
  public void kakaoLogin() {
  }

}
