package sorok.soroksorok.follow.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import sorok.soroksorok.user.entity.User;

@ApiModel(value = "팔로우 정보 응답")
@Getter
@Builder
public class FollowRes {

  private String nickname;
  private String imageUrl;

  public static FollowRes makeDto(User user) {
    return FollowRes
        .builder()
        .imageUrl(user.getImageUrl())
        .nickname(user.getNickname())
        .build();
  }

}
