package sorok.soroksorok.follow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sorok.soroksorok.follow.entity.Follow;
import sorok.soroksorok.user.entity.User;

public interface FollowRepository extends JpaRepository<Follow, Long> {

  long deleteByFolloweeAndFollower(User followee, User follower);


}
