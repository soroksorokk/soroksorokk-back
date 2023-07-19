package sorok.soroksorok.temp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TempController {

  @GetMapping("/temporary")
  public String temporary() {
    return "연결 완료";
  }

}
