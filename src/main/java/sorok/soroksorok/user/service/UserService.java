package sorok.soroksorok.user.service;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import sorok.soroksorok.user.dto.UserProfileEditReq;
import sorok.soroksorok.user.dto.UserProfileRes;
import sorok.soroksorok.user.entity.User;

public interface UserService {

  User getUserEntityByNickname(String nickname);

  UserProfileRes selectMyProfile(User user);

  void editMyProfile(MultipartFile image, UserProfileEditReq req, User user)
      throws IOException;
}
