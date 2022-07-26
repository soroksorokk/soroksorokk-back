package sorok.soroksorok.follow.service;

import java.util.List;
import sorok.soroksorok.follow.dto.UserRes;
import sorok.soroksorok.user.entity.User;

public interface FollowService {

  void followUser(User user, String nickname);

  void unfollowUser(User user, String nickname);

  List<UserRes> selectMyFollowingUsers(User user);

  List<UserRes> selectMyFollowerUsers(User user);

  Long countMyFollowingCount(User user);

  Long countMyFollowerCount(User user);
}
