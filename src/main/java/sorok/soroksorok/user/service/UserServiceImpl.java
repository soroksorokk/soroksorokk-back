package sorok.soroksorok.user.service;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sorok.soroksorok.follow.service.FollowService;
import sorok.soroksorok.global.s3.S3Upload;
import sorok.soroksorok.user.dto.UserProfileReq;
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
    return userRepository.findByNickname(nickname).orElseThrow(() -> new RuntimeException());
  }

  @Override
  @Transactional(readOnly = true)
  public UserProfileRes selectMyProfile(User user) {
    Long followingCount = followService.countMyFollowingCount(user);
    Long followerCount = followService.countMyFollowerCount(user);
    return UserProfileRes.createDto(user, followingCount, followerCount);
  }

  @Override
  @Transactional
  public void editMyProfile(MultipartFile image, UserProfileReq req, User user)
      throws IOException {
    String imageUrl;

    if (image.isEmpty()) {
      imageUrl = "default";
    } else {
      imageUrl = s3Upload.upload(image);
    }

    user.editProfile(req, imageUrl);
  }


}
