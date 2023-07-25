package sorok.soroksorok.global.config.temp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sorok.soroksorok.user.entity.Role;
import sorok.soroksorok.user.entity.User;
import sorok.soroksorok.user.repository.UserRepository;

@Slf4j
@RequiredArgsConstructor
@Service
public class InitData implements ApplicationRunner {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  @Transactional
  public void run(ApplicationArguments args) throws Exception {
    User user = User.builder()
        .email("test@test.com")
        .password(passwordEncoder.encode("1234"))
        .nickname("test")
        .role(Role.USER)
        .imageUrl("imageUrl")
        .agreeOption1(true)
        .agreeOption2(true)
        .build();
    userRepository.save(user);
  }

}
