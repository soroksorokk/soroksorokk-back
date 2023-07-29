package sorok.soroksorok.follow.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sorok.soroksorok.follow.entity.Follow;
import sorok.soroksorok.follow.dto.FollowRes;
import sorok.soroksorok.user.entity.User;
import sorok.soroksorok.follow.repository.FollowRepository;
import sorok.soroksorok.user.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {

  private final FollowRepository followRepository;
  //  private final UserService userService;
  private final UserRepository userRepository;

  @Override
  @Transactional
  public void followUser(User follower, String nickname) {
    User followee = userRepository.findByNickname(nickname).orElseThrow(() -> new RuntimeException());

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
    User followee = userRepository.findByNickname(nickname).orElseThrow(() -> new RuntimeException());
    long cnt = followRepository.deleteByFolloweeAndFollower(followee, follower);

    if (cnt == 0) {
      throw new RuntimeException();
    }
  }

  @Override
  @Transactional
  public List<FollowRes> selectMyFollowingUsers(User user) {
    List<Follow> followingEntities = followRepository.findByFollower(user);
    return followingEntities
        .stream()
        .map(Follow::getFollowee)
        .map(FollowRes::makeDto)
        .collect(Collectors.toList());
  }

  @Override
  @Transactional
  public List<FollowRes> selectMyFollowerUsers(User user) {
    List<Follow> followerEntities = followRepository.findByFollowee(user);
    return followerEntities
        .stream()
        .map(Follow::getFollower)
        .map(FollowRes::makeDto)
        .collect(Collectors.toList());
  }

  @Override
  @Transactional(readOnly = true)
  public Long countMyFollowingCount(User user) {
    return followRepository.countByFollower(user);
  }

  @Override
  @Transactional(readOnly = true)
  public Long countMyFollowerCount(User user) {
    return followRepository.countByFollowee(user);
  }
}
