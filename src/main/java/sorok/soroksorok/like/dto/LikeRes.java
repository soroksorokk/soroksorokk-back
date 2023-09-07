package sorok.soroksorok.like.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@ApiModel(value = "팔로우 정보 응답")
@Getter
@Builder
public class LikeRes {

  @ApiModelProperty(value="내가 좋아요 눌렀는지 여부", example = "true", required = true)
  private Boolean liked;

  @ApiModelProperty(value="좋아요 갯수", example = "1", required = true)
  private Long count;

}
