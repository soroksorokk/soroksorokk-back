package sorok.soroksorok.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sorok.soroksorok.user.dto.UserProfileEditReq;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@Table(name = "USERS")
@AllArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long id;

  private String email; // 이메일

  private String password; // 비밀번호

  private String nickname; // 닉네임

  private String imageUrl; // 프로필 이미지

  private String description;

  private String tags;

  @Enumerated(EnumType.STRING)
  private SocialType socialType; // KAKAO, NAVER, GOOGLE

  @Enumerated(EnumType.STRING)
  private Role role;

  private String socialId; // 로그인한 소셜 타입의 식별자 값 (일반 로그인인 경우 null)

  private String refreshToken; // 리프레시 토큰

  private Boolean agreeOption1;

  private Boolean agreeOption2;

  public void updateRefreshToken(String updateRefreshToken) {
    this.refreshToken = updateRefreshToken;
  }

  public void editProfileDescription(UserProfileEditReq req) {
    this.nickname = req.getNickname();
    this.description = req.getDescription();
    this.tags = req.getTags();
  }

  public void editProfileImage(String imageUrl) {
    this.imageUrl = imageUrl;
  }
}