package sorok.soroksorok.follow.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sorok.soroksorok.follow.entity.Follow;
import sorok.soroksorok.user.entity.User;
import sorok.soroksorok.follow.repository.FollowRepository;
import sorok.soroksorok.user.service.UserService;

@Slf4j
@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {

  private final FollowRepository followRepository;
  private final UserService userService;

  @Override
  @Transactional
  public void followUser(User follower, String nickname) {
    User followee = userService.getUserEntityByNickname(nickname);

    Follow followEntity = Follow
        .builder()
        .followee(followee)
        .follower(follower)
        .build();

    followRepository.save(followEntity);
  }

  @Override
  @Transactional
  public void unfollowUser(User follower, String nickname) {
    User followee = userService.getUserEntityByNickname(nickname);
    long cnt = followRepository.deleteByFolloweeAndFollower(followee, follower);

    if (cnt == 0) {
      throw new RuntimeException();
    }
  }
}
