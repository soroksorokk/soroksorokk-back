package sorok.soroksorok.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sorok.soroksorok.user.dto.SignUpRequest;
import sorok.soroksorok.user.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping("/sign-up")
  public String signUp(@RequestBody SignUpRequest signUpRequest) throws Exception {
    userService.signUp(signUpRequest);
    return "회원가입 성공";
  }

  @GetMapping("/jwt-test")
  public String jwtTest() {
    return "jwtTest 요청 성공";
  }

}