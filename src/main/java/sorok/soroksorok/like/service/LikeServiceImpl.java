package sorok.soroksorok.like.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sorok.soroksorok.comment.entity.Comment;
import sorok.soroksorok.feed.entity.Feed;
import sorok.soroksorok.like.entity.CommentLike;
import sorok.soroksorok.like.entity.FeedLike;
import sorok.soroksorok.like.repository.CommentLikeRepository;
import sorok.soroksorok.like.repository.FeedLikeRepository;
import sorok.soroksorok.user.entity.User;

@Slf4j
@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

  private final CommentLikeRepository commentLikeRepository;
  private final FeedLikeRepository feedLikeRepository;

  @Override
  @Transactional
  public void likeFeed(Feed feed, User user) {
    validateIfUserAlreadyLiked(feed, user);
    FeedLike feedLike = FeedLike.builder().feed(feed).user(user).build();
    feedLikeRepository.save(feedLike);
  }

  @Override
  @Transactional
  public void unlikeFeed(Feed feed, User user) {
    FeedLike feedLike = feedLikeRepository.findByUserAndFeed(user, feed)
        .orElseThrow(RuntimeException::new);
    feedLikeRepository.delete(feedLike);
  }

  @Override
  @Transactional
  public void likeComment(Comment comment, User user) {
    validateIfUserAlreadyLiked(comment, user);
    CommentLike commentLike = CommentLike.builder().comment(comment).user(user).build();
    commentLikeRepository.save(commentLike);
  }

  @Override
  @Transactional
  public void unlikeComment(Comment comment, User user) {
    CommentLike commentLike = commentLikeRepository.findByUserAndComment(user, comment)
        .orElseThrow(RuntimeException::new);
    commentLikeRepository.delete(commentLike);
  }
  private void validateIfUserAlreadyLiked(Object obj, User user) {
    if (obj instanceof Feed) {
      Feed feed = (Feed) obj;
      if (feedLikeRepository.existsByUserAndFeed(user, feed)) {
        throw new RuntimeException();
      }
    } else if (obj instanceof Comment) {
      Comment comment = (Comment) obj;
      if (commentLikeRepository.existsByUserAndComment(user, comment)) {
        throw new RuntimeException();
      }
    } else {
      throw new RuntimeException();
    }
  }

}
