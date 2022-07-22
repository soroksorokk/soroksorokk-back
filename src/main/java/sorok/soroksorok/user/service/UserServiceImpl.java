package sorok.soroksorok.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sorok.soroksorok.user.entity.User;
import sorok.soroksorok.user.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

  private final UserRepository userRepository;

  @Override
  @Transactional
  public User getUserEntityByNickname(String nickname) {
    return userRepository.findByNickname(nickname).orElseThrow(() -> new RuntimeException());
  }

}
