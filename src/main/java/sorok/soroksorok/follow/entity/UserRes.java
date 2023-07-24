package sorok.soroksorok.follow.entity;

import lombok.Builder;
import lombok.Getter;
import sorok.soroksorok.user.entity.User;

@Getter
@Builder
public class UserRes {

  private String nickname;

  public static UserRes makeDto(User user) {
    return UserRes
        .builder()
        .nickname(user.getNickname())
        .build();
  }

}
