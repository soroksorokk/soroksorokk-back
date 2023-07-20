package sorok.soroksorok.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sorok.soroksorok.global.s3.S3Upload;
import sorok.soroksorok.auth.dto.SignUpRequest;
import sorok.soroksorok.auth.entity.Role;
import sorok.soroksorok.auth.entity.User;
import sorok.soroksorok.auth.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final S3Upload s3Upload;

  @Transactional
  public void signUp(SignUpRequest userSignUpDto, MultipartFile image) throws Exception {

    if (userRepository.findByEmail(userSignUpDto.getEmail()).isPresent()) {
      throw new Exception("이미 존재하는 이메일입니다.");
    }

    if (userRepository.findByNickname(userSignUpDto.getNickname()).isPresent()) {
      throw new Exception("이미 존재하는 닉네임입니다.");
    }

    String imageUrl;
    if (!image.isEmpty()) {
      imageUrl = s3Upload.upload(image);
    } else {
      imageUrl = "https://soroksorok.s3.ap-northeast-2.amazonaws.com/default.jpg";
    }

    System.out.println(imageUrl);

    User user = User.builder()
        .email(userSignUpDto.getEmail())
        .password(passwordEncoder.encode(userSignUpDto.getPassword()))
        .nickname(userSignUpDto.getNickname())
        .imageUrl(imageUrl)
        .agreeOption1(userSignUpDto.getOption1())
        .agreeOption2(userSignUpDto.getOption2())
        .role(Role.USER)
        .build();

    userRepository.save(user);
  }
}
