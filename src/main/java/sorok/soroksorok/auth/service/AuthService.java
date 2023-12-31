package sorok.soroksorok.auth.service;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sorok.soroksorok.global.s3.S3Upload;
import sorok.soroksorok.auth.dto.SignUpRequest;
import sorok.soroksorok.user.entity.Role;
import sorok.soroksorok.user.entity.User;
import sorok.soroksorok.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final S3Upload s3Upload;

  @Transactional
  public void signUp(SignUpRequest userSignUpDto, MultipartFile image) throws Exception {

    String imageUrl = uploadImage(image);

    User user = User.builder()
        .email(userSignUpDto.getEmail())
        .description("")
        .tags("")
        .password(passwordEncoder.encode(userSignUpDto.getPassword()))
        .nickname(userSignUpDto.getNickname())
        .imageUrl(imageUrl)
        .agreeOption1(userSignUpDto.getOption1())
        .agreeOption2(userSignUpDto.getOption2())
        .role(Role.USER)
        .build();

    userRepository.save(user);
  }

  private String uploadImage(MultipartFile image) throws IOException {
    String imageUrl;

    if (image == null) {
      imageUrl = "https://soroksorok.s3.ap-northeast-2.amazonaws.com/default.jpg";
      return imageUrl;
    }

    if (!image.isEmpty()) {
      imageUrl = s3Upload.upload(image);
    } else {
      imageUrl = "https://soroksorok.s3.ap-northeast-2.amazonaws.com/default.jpg";
    }
    return imageUrl;
  }

  public void validateEmail(String value) throws Exception {
    if (userRepository.findByEmail(value).isPresent()) {
      throw new Exception("이미 존재하는 이메일입니다.");
    }
  }

  public void validateNickname(String value) throws Exception {
    if (userRepository.findByNickname(value).isPresent()) {
      throw new Exception("이미 존재하는 닉네임입니다.");
    }
  }
}
