package sorok.soroksorok.global.login;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sorok.soroksorok.user.entity.User;
import sorok.soroksorok.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetailsImpl loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("해당 이메일이 존재하지 않습니다."));

    return new UserDetailsImpl(user);

//    return org.springframework.security.core.userdetails.User.builder()
//        .username(user.getEmail())
//        .password(user.getPassword())
//        .roles(user.getRole().name())
//        .build();
  }
}