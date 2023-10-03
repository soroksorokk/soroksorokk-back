package sorok.soroksorok.feed.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sorok.soroksorok.feed.entity.TempFeed;
import sorok.soroksorok.user.entity.User;
import sorok.soroksorok.feed.entity.Feed;
import sorok.soroksorok.feed.entity.Mood;

@ApiModel(value = "피드 작성 요청")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FeedReq {

  @ApiModelProperty(value="내용", example = "내용입니다.", required = true)
  private String content;

  @ApiModelProperty(value="제목", example = "제목입니다.", required = true)
  private String title;

  @ApiModelProperty(value="기분", example = "HAPPY / SAD / GLOOMY ...", required = true)
  private Mood mood;

  @ApiModelProperty(value="가수 이름", example = "나훈아", required = true)
  private String artist;

  @ApiModelProperty(value="노래 이름", example = "홍시", required = true)
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

  public TempFeed toTempEntity(String imageUrl, User user) {
    return TempFeed
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
