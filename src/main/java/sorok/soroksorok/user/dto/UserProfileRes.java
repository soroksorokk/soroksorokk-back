package sorok.soroksorok.user.dto;

import lombok.Builder;
import lombok.Getter;
import sorok.soroksorok.user.entity.User;

@Getter
@Builder
public class UserProfileRes {

  private String email;

  private String imageUrl;

  private String nickname;

  private Long followerCnt;

  private Long followingCnt;

  public static UserProfileRes createDto(User user, Long followerCnt, Long followingCnt) {
    return UserProfileRes
        .builder()
        .email(user.getEmail())
        .imageUrl(user.getImageUrl())
        .nickname(user.getNickname())
        .followerCnt(followerCnt)
        .followingCnt(followingCnt)
        .build();
  }
}
