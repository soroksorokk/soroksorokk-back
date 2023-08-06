package sorok.soroksorok.comment.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sorok.soroksorok.comment.entity.Comment;
import sorok.soroksorok.feed.entity.Feed;
import sorok.soroksorok.user.entity.User;

@ApiModel(value = "피드 작성 요청")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentReq {

  @ApiModelProperty(value="내용", example = "내용입니다.", required = true)
  private String content;

  public Comment toEntity(Feed feed, CommentReq req, User user) {
    return Comment
        .builder()
        .content(req.getContent())
        .user(user)
        .feed(feed)
        .build();
  }

}
