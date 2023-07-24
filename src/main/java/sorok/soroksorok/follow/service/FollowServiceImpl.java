package sorok.soroksorok.follow.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sorok.soroksorok.follow.entity.Follow;
import sorok.soroksorok.follow.entity.UserRes;
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

  @Override
  @Transactional
  public List<UserRes> selectMyFollowingUsers(User user) {
    List<Follow> followingEntities = followRepository.findByFollower(user);
    return followingEntities
        .stream()
        .map(Follow::getFollowee)
        .map(UserRes::makeDto)
        .collect(Collectors.toList());
  }

  @Override
  @Transactional
  public List<UserRes> selectMyFollowerUsers(User user) {
    List<Follow> followerEntities = followRepository.findByFollowee(user);
    return followerEntities
        .stream()
        .map(Follow::getFollower)
        .map(UserRes::makeDto)
        .collect(Collectors.toList());
  }
}
