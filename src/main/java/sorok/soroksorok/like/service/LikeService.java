package sorok.soroksorok.like.service;

import sorok.soroksorok.comment.entity.Comment;
import sorok.soroksorok.comment.entity.Reply;
import sorok.soroksorok.feed.entity.Feed;
import sorok.soroksorok.user.entity.User;

public interface LikeService {

  void likeFeed(Feed feedId, User user);

  void unlikeFeed(Feed feedId, User user);

  void likeComment(Comment commentId, User user);

  void unlikeComment(Comment commentId, User user);

  Long selectFeedLikeCount(Feed feed);

  Long selectCommentLikeCount(Comment comment);

  Long selectReplyLikeCount(Reply reply);

  void likeReply(Reply reply, User user);

  void unlikeReply(Reply reply, User user);
}
