package sorok.soroksorok.like.service;

import sorok.soroksorok.comment.entity.Comment;
import sorok.soroksorok.feed.entity.Feed;
import sorok.soroksorok.user.entity.User;

public interface LikeService {

  void likeFeed(Feed feedId, User user);

  void unlikeFeed(Feed feedId, User user);

  void likeComment(Comment commentId, User user);

  void unlikeComment(Comment commentId, User user);
}
