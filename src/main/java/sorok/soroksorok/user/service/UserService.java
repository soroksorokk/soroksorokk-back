package sorok.soroksorok.user.service;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import sorok.soroksorok.user.dto.UserProfileEditReq;
import sorok.soroksorok.user.dto.UserProfileRes;
import sorok.soroksorok.user.entity.User;

public interface UserService {

  User getUserEntityByNickname(String nickname);

  UserProfileRes selectMyProfile(User user);

  void editMyProfileImage(MultipartFile image, User user) throws IOException;

  void editMyProfileDescription(UserProfileEditReq req, User user);

  UserProfileRes selectUserProfileByNickname(String nickname);

}
