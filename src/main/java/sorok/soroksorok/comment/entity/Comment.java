package sorok.soroksorok.comment.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sorok.soroksorok.comment.dto.CommentEditReq;
import sorok.soroksorok.feed.entity.Feed;
import sorok.soroksorok.global.entity.BaseEntity;
import sorok.soroksorok.user.entity.User;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  private Feed feed;

  @Builder
  public Comment(String content, User user, Feed feed) {
    this.content = content;
    this.user = user;
    this.feed = feed;
  }

  public void editComment(CommentEditReq req) {
    this.content = req.getContent();
  }

  public boolean isAccessibleUser(User user) {
    return this.user.equals(user);
  }
}
