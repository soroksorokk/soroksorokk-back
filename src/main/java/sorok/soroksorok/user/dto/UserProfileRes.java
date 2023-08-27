package sorok.soroksorok.user.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import sorok.soroksorok.user.entity.User;

@ApiModel(value = "유저 프로필 조회 응답")
@Getter
@Builder
public class UserProfileRes {

  private String description;

  private String tags;

  private String email;

  private String imageUrl;

  private String nickname;

  private Long followerCnt;

  private Long followingCnt;

  public static UserProfileRes of(User user, Long followerCnt, Long followingCnt) {
    return UserProfileRes
        .builder()
        .email(user.getEmail())
        .description(user.getDescription())
        .tags(user.getTags())
        .imageUrl(user.getImageUrl())
        .nickname(user.getNickname())
        .followerCnt(followerCnt)
        .followingCnt(followingCnt)
        .build();
  }
}
