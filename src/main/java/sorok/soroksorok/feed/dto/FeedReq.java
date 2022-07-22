package sorok.soroksorok.feed.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sorok.soroksorok.user.entity.User;
import sorok.soroksorok.feed.entity.Feed;
import sorok.soroksorok.feed.entity.Mood;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FeedReq {

  private String content;

  private String title;

  private Mood mood;

  private String artist;

  private String music;

  public Feed toEntity(String imageUrl, User user) {
    return Feed
        .builder()
        .title(this.title)
        .content(this.content)
        .artist(this.artist)
        .music(this.music)
        .mood(this.mood)
        .imageUrl(imageUrl)
        .user(user)
        .build();
  }

}
