package sorok.soroksorok.feed.dto;

import io.swagger.annotations.ApiModel;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import sorok.soroksorok.feed.entity.Feed;
import sorok.soroksorok.feed.entity.Mood;

@ApiModel(value = "피드 조회 응답")
@Getter
@Builder
public class FeedPagingRes {

  private Long id;

  private String nickname;

  private String profileImage;

  private String title;

  private Mood mood;

  private LocalDateTime createdAt;

  public static FeedPagingRes of(Feed feed) {
    return FeedPagingRes
        .builder()
        .id(feed.getId())
        .nickname(feed.getUser().getNickname())
        .profileImage(feed.getUser().getImageUrl())
        .title(feed.getTitle())
        .mood(feed.getMood())
        .createdAt(feed.getCreatedAt())
        .build();
  }

}
