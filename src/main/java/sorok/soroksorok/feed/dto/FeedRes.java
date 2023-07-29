package sorok.soroksorok.feed.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import sorok.soroksorok.feed.entity.Feed;
import sorok.soroksorok.feed.entity.Mood;

@ApiModel(value = "피드 조회 응답")
@Getter
@Builder
public class FeedRes {

  private String title;

  private String content;

  private Mood mood;

  private String imageUrl;

  private LocalDateTime createdAt;

  private LocalDateTime modifiedAt;

  public static FeedRes createDto(Feed feed) {
    return FeedRes
        .builder()
        .title(feed.getTitle())
        .content(feed.getContent())
        .mood(feed.getMood())
        .imageUrl(feed.getImageUrl())
        .createdAt(feed.getCreatedAt())
        .modifiedAt(feed.getModifiedAt())
        .build();
  }

}
