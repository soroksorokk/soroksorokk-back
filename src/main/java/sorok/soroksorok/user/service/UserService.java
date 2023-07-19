package sorok.soroksorok.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sorok.soroksorok.user.dto.SignUpRequest;
import sorok.soroksorok.user.entity.Role;
import sorok.soroksorok.user.entity.User;
import sorok.soroksorok.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  public void signUp(SignUpRequest userSignUpDto) throws Exception {

    if (userRepository.findByEmail(userSignUpDto.getEmail()).isPresent()) {
      throw new Exception("이미 존재하는 이메일입니다.");
    }

    if (userRepository.findByNickname(userSignUpDto.getNickname()).isPresent()) {
      throw new Exception("이미 존재하는 닉네임입니다.");
    }

    User user = User.builder()
        .email(userSignUpDto.getEmail())
        .password(userSignUpDto.getPassword())
        .nickname(userSignUpDto.getNickname())
        .role(Role.USER)
        .build();

    user.passwordEncode(passwordEncoder);
    userRepository.save(user);
  }
}
