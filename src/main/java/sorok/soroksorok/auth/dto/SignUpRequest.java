package sorok.soroksorok.auth.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "회원가입 요청")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SignUpRequest {

  @ApiModelProperty(value="이메일", example = "test@test.com", required = true)
  private String email;

  @ApiModelProperty(value="비밀번호", example = "pssword", required = true)
  private String password;

  @ApiModelProperty(value="닉네임", example = "어피치", required = true)
  private String nickname;

  @ApiModelProperty(value="선택 동의 1", example = "false", required = true)
  private Boolean option1;

  @ApiModelProperty(value="선택 동의 2", example = "true", required = true)
  private Boolean option2;

}
