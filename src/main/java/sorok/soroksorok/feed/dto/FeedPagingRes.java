package sorok.soroksorok.feed.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import sorok.soroksorok.feed.entity.Feed;
import sorok.soroksorok.feed.entity.Mood;

@ApiModel(value = "피드 조회 응답")
@Getter
@Builder
public class FeedPagingRes {

  @ApiModelProperty(value="게시글 ID", example = "1", required = true)
  private Long id;

  @ApiModelProperty(value="작성자 닉네임", example = "닉네임", required = true)
  private String nickname;

  @ApiModelProperty(value="프로필 이미지 URL", example = "URL", required = true)
  private String profileImage;

  @ApiModelProperty(value="제목", example = "제목", required = true)
  private String title;

  @ApiModelProperty(value="분위기", example = "ANGRY", required = true)
  private Mood mood;

  @ApiModelProperty(value="작성 시간", example = "", required = true)
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
