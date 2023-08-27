package sorok.soroksorok.like.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sorok.soroksorok.comment.entity.Reply;
import sorok.soroksorok.feed.entity.Feed;
import sorok.soroksorok.user.entity.User;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(uniqueConstraints = {
    @UniqueConstraint(name = "rplRcmndCnst", columnNames = {"user_id", "reply_id"}),
})
public class ReplyLike extends Like {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "reply_id")
  private Reply reply;

  @Builder
  public ReplyLike(User user, Reply reply) {
    this.user = user;
    this.reply = reply;
  }


}
