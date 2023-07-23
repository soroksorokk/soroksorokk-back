package sorok.soroksorok.auth.controller;

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

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/sign-up")
  @ResponseStatus(HttpStatus.CREATED)
  public void signUp(
      @RequestPart(required = false) MultipartFile image,
      @RequestPart SignUpRequest signUpRequest
  ) throws Exception {
    authService.signUp(signUpRequest, image);
  }

}