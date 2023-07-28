package sorok.soroksorok.global.config.temp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TempController {

  @GetMapping("/temp")
  public String temporary() {
    return "연결 완료";
  }

  @GetMapping("/aa")
  public String aa() {
    return "a";
  }
}
