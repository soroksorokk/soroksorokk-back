package sorok.soroksorok.comment.dto;

import io.swagger.annotations.ApiModelProperty;
import sorok.soroksorok.comment.entity.Comment;
import sorok.soroksorok.comment.entity.Reply;
import sorok.soroksorok.user.entity.User;

public class ReplyReq {

  @ApiModelProperty(value="내용", example = "내용입니다.", required = true)
  private String content;

  public Reply toEntity(Comment parentComment, ReplyReq req, User user) {
    return Reply
        .builder()
        .content(req.getContent())
        .user(user)
        .parentComment(parentComment)
        .build();
  }

}
