package sorok.soroksorok.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "유저 프로필 수정 요청")
@Getter
@NoArgsConstructor
public class UserProfileEditReq {

  @ApiModelProperty(value="닉네임", example = "nickname", required = true)
  private String nickname;

  @ApiModelProperty(value="태그", example = "가나다/라마바/사아자", required = true)
  private String tags;

  @ApiModelProperty(value="설명", example = "설명입니다.", required = true)
  private String description;

}
