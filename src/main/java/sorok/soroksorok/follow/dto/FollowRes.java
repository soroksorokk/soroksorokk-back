package sorok.soroksorok.follow.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import sorok.soroksorok.user.entity.User;

@ApiModel(value = "팔로우 정보 응답")
@Getter
@Builder
public class FollowRes {

  @ApiModelProperty(value="닉네임", example = "닉네임", required = true)
  private String nickname;

  @ApiModelProperty(value="사용자 이미지 URL", example = "1", required = true)
  private String imageUrl;

  public static FollowRes makeDto(User user) {
    return FollowRes
        .builder()
        .imageUrl(user.getImageUrl())
        .nickname(user.getNickname())
        .build();
  }

}
