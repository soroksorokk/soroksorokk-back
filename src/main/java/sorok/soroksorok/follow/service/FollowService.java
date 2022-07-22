package sorok.soroksorok.follow.service;

import sorok.soroksorok.user.entity.User;

public interface FollowService {

  void followUser(User user, String nickname);

  void unfollowUser(User user, String nickname);
}
