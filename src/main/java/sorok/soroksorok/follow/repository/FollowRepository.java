package sorok.soroksorok.follow.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import sorok.soroksorok.follow.entity.Follow;
import sorok.soroksorok.user.entity.User;

public interface FollowRepository extends JpaRepository<Follow, Long> {

  List<Follow> findByFollower(User follower);

  List<Follow> findByFollowee(User followee);

  long deleteByFolloweeAndFollower(User followee, User follower);


}
