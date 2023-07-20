package sorok.soroksorok.auth.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SignUpRequest {

  private String email;
  private String password;
  private String nickname;
  private Boolean option1;
  private Boolean option2;

}
