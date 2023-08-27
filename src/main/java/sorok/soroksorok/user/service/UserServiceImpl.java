package sorok.soroksorok.user.service;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sorok.soroksorok.follow.service.FollowService;
import sorok.soroksorok.global.s3.S3Upload;
import sorok.soroksorok.user.dto.UserProfileEditReq;
import sorok.soroksorok.user.dto.UserProfileRes;
import sorok.soroksorok.user.entity.User;
import sorok.soroksorok.user.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

  private final UserRepository userRepository;
  private final FollowService followService;
  private final S3Upload s3Upload;

  @Override
  @Transactional
  public User getUserEntityByNickname(String nickname) {
    return userRepository.findByNickname(nickname).orElseThrow(RuntimeException::new);
  }

  @Override
  @Transactional(readOnly = true)
  public UserProfileRes selectMyProfile(User user) {
    Long followingCount = followService.countMyFollowingCount(user);
    Long followerCount = followService.countMyFollowerCount(user);
    return UserProfileRes.of(user, followingCount, followerCount);
  }

  @Override
  @Transactional
  public void editMyProfileImage(MultipartFile image, User user) throws IOException {
    String imageUrl;
    User reqUser = getUserEntityByNickname(user.getNickname());

    if (image.isEmpty()) {
      imageUrl = "default.jpg";
    } else {
      imageUrl = s3Upload.upload(image);
    }

    reqUser.editProfileImage(imageUrl);
  }

  @Override
  @Transactional
  public void editMyProfileDescription(UserProfileEditReq req, User user) {
    if (userRepository.existsByNickname(req.getNickname())) {
      throw new RuntimeException("닉네임 중복");
    }

    User reqUser = getUserEntityByNickname(user.getNickname());
    reqUser.editProfileDescription(req);
  }

  @Override
  public UserProfileRes selectUserProfileByNickname(String nickname) {
    User user = getUserEntityByNickname(nickname);
    return selectMyProfile(user);
  }

}
