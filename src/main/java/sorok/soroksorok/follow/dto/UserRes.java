package sorok.soroksorok.follow.dto;

import lombok.Builder;
import lombok.Getter;
import sorok.soroksorok.user.entity.User;

@Getter
@Builder
public class UserRes {

  private String nickname;
  private String imageUrl;

  public static UserRes makeDto(User user) {
    return UserRes
        .builder()
        .imageUrl(user.getImageUrl())
        .nickname(user.getNickname())
        .build();
  }

}
