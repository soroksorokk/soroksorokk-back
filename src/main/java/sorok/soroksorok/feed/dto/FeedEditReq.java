package sorok.soroksorok.feed.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel(value = "피드 수정 요청")
public class FeedEditReq {

  @ApiModelProperty(value="제목", example = "제목입니다.", required = true)
  private String title;

  @ApiModelProperty(value="내용", example = "내용입니다.", required = true)
  private String content;

  @ApiModelProperty(value="태그", example = "1, 2, 3", required = true)
  private String tags;

}
