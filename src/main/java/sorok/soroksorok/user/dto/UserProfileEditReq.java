package sorok.soroksorok.user.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;

@ApiModel(value = "유저 프로필 수정 요청")
@Getter
public class UserProfileEditReq {

  private String nickname;

  private String tags;

  private String description;

}
