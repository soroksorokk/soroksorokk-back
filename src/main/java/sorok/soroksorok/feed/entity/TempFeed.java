package sorok.soroksorok.feed.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sorok.soroksorok.feed.dto.FeedEditReq;
import sorok.soroksorok.global.entity.BaseEntity;
import sorok.soroksorok.user.entity.User;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TempFeed extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  private String content;

  private String imageUrl;

  private String artist;

  private String music;

  @ManyToOne(fetch = FetchType.LAZY)
  private User user;

  @Enumerated(EnumType.STRING)
  private Mood mood;

  @Builder
  public TempFeed(String title, String content, String imageUrl, String artist, String music, User user,
      Mood mood) {
    this.title = title;
    this.content = content;
    this.imageUrl = imageUrl;
    this.artist = artist;
    this.music = music;
    this.user = user;
    this.mood = mood;
  }

  public void editFeed(FeedEditReq req, String imageUrl) {
    this.title = req.getTitle();
    this.content = req.getContent();
    this.imageUrl = imageUrl;
  }

  public boolean isWroteByUser(User user) {
    return this.user.equals(user);
  }

}
