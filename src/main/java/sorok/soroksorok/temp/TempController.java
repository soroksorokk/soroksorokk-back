package sorok.soroksorok.temp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TempController {

  @GetMapping("/temp")
  public String temporary() {
    return "연결 완료";
  }

}
