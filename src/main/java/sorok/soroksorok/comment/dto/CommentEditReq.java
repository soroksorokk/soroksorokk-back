package sorok.soroksorok.comment.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel(value = "댓글 수정 요청")
public class CommentEditReq {

  @ApiModelProperty(value="댓글 내용", example = "test comment", required = true)
  private String content;

}
