package sorok.soroksorok.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import sorok.soroksorok.user.entity.User;

@ApiModel(value = "유저 프로필 조회 응답")
@Getter
@Builder
public class UserProfileRes {

  @ApiModelProperty(value="설명", example = "설명입니다.", required = true)
  private String description;

  @ApiModelProperty(value="태그", example = "가나다/라마바/사아자", required = true)
  private String tags;

  @ApiModelProperty(value="이메일", example = "nickname@email.com", required = true)
  private String email;

  @ApiModelProperty(value="프로필 이미지", example = "URL", required = true)
  private String imageUrl;

  @ApiModelProperty(value="닉네임", example = "nickname", required = true)
  private String nickname;

  @ApiModelProperty(value="팔로워 숫자", example = "1", required = true)
  private Long followerCnt;

  @ApiModelProperty(value="팔로잉 숫자", example = "1", required = true)
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
