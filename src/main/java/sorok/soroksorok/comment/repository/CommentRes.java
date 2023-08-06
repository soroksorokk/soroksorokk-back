package sorok.soroksorok.comment.repository;

import java.time.LocalDateTime;
import sorok.soroksorok.feed.entity.Feed;
import sorok.soroksorok.user.entity.User;

/**
 * A Projection for the {@link sorok.soroksorok.comment.entity.Comment} entity
 */
public interface CommentRes {

  LocalDateTime getCreatedAt();

  String getContent();

  User getUser();

  Feed getFeed();
}