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

  @ApiModelProperty(value="아티스트", example = "아티스트", required = true)
  private String artist;

  @ApiModelProperty(value="노래제목", example = "노래제목", required = true)
  private String music;

  @ApiModelProperty(value="분위기", example = "ANGRY", required = true)
  private Mood mood;

  @ApiModelProperty(value="작성 시간", example = "", required = true)
  private LocalDateTime createdAt;

  @ApiModelProperty(value="작성 시간", example = "", required = true)
  private String imageUrl;

  @ApiModelProperty(value="좋아요 갯수", example = "2", required = true)
  private Integer likeCount;

  @ApiModelProperty(value="글 내용", example = "내용", required = true)
  private String content;

  @ApiModelProperty(value="태그", example = "[1, 2, 3]", required = true)
  private List<String> tags;

  public static FeedPagingRes of(Feed feed) {
    return FeedPagingRes
        .builder()
        .id(feed.getId())
        .nickname(feed.getUser().getNickname())
        .profileImage(feed.getUser().getImageUrl())
        .artist(feed.getArtist())
        .music(feed.getMusic())
        .title(feed.getTitle())
        .mood(feed.getMood())
        .createdAt(feed.getCreatedAt())
        .imageUrl(feed.getImageUrl())
        .content(feed.getContent())
        .tags(feed.getTags().stream().map(Tags::getContent).collect(Collectors.toList()))
        .build();
  }

}
