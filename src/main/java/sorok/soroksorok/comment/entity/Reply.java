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
import sorok.soroksorok.comment.dto.ReplyEditReq;
import sorok.soroksorok.global.entity.BaseEntity;
import sorok.soroksorok.user.entity.User;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reply extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  private Comment parentComment;

  @Builder
  public Reply(String content, User user, Comment parentComment) {
    this.content = content;
    this.user = user;
    this.parentComment = parentComment;
  }

  public void editComment(ReplyEditReq req) {
    this.content = req.getContent();
  }

  public boolean isAccessibleUser(User user) {
    return this.user.equals(user);
  }

}
