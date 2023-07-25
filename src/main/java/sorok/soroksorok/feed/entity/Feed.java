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
import sorok.soroksorok.user.entity.User;
import sorok.soroksorok.global.entity.BaseEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Feed extends BaseEntity {

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
  public Feed(String title, String content, String imageUrl, String artist, String music, User user,
      Mood mood) {
    this.title = title;
    this.content = content;
    this.imageUrl = imageUrl;
    this.artist = artist;
    this.music = music;
    this.user = user;
    this.mood = mood;
  }
}