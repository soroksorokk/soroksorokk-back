package sorok.soroksorok.feed.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import sorok.soroksorok.feed.entity.Feed;
import sorok.soroksorok.feed.entity.Mood;
import sorok.soroksorok.feed.entity.Tags;
import sorok.soroksorok.feed.entity.TempFeed;

@ApiModel(value = "피드 조회 응답")
@Getter
@Builder
public class FeedRes {

  @ApiModelProperty(value="게시글 ID", example = "1", required = true)
  private Long id;

  @ApiModelProperty(value="작성자 닉네임", example = "닉네임", required = true)
  private String nickname;

  @ApiModelProperty(value="게시글 작성자 프로필 이미지", example = "URL", required = true)
  private String profileImage;

  @ApiModelProperty(value="제목", example = "제목", required = true)
  private String title;

  @ApiModelProperty(value="내용", example = "내용", required = true)
  private String content;

  @ApiModelProperty(value="분위기", example = "ANGRY", required = true)
  private Mood mood;

  @ApiModelProperty(value="게시글 이미지 URL", example = "URL", required = true)
  private String imageUrl;

  @ApiModelProperty(value="게시글 작성시간", example = "", required = true)
  private LocalDateTime createdAt;

  @ApiModelProperty(value="게시글 수정 시간", example = "", required = true)
  private LocalDateTime modifiedAt;

  @ApiModelProperty(value="태그", example = "[1, 2, 3]", required = true)
  private List<String> tags;

  public static FeedRes of(Feed feed) {
    return FeedRes
        .builder()
        .id(feed.getId())
        .nickname(feed.getUser().getNickname())
        .profileImage(feed.getUser().getImageUrl())
        .title(feed.getTitle())
        .content(feed.getContent())
        .mood(feed.getMood())
        .imageUrl(feed.getImageUrl())
        .createdAt(feed.getCreatedAt())
        .modifiedAt(feed.getModifiedAt())
        .tags(feed.getTags().stream().map(Tags::getContent).collect(Collectors.toList()))
        .build();
  }

  public static FeedRes of(TempFeed feed) {
    return FeedRes
        .builder()
        .id(feed.getId())
        .nickname(feed.getUser().getNickname())
        .profileImage(feed.getUser().getImageUrl())
        .title(feed.getTitle())
        .content(feed.getContent())
        .mood(feed.getMood())
        .imageUrl(feed.getImageUrl())
        .createdAt(feed.getCreatedAt())
        .modifiedAt(feed.getModifiedAt())
        .build();
  }

}
