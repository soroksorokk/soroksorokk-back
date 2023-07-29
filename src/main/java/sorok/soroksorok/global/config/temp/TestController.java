package sorok.soroksorok.global.config.temp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Test API - 서버 연결 확인 API")
@RestController
public class TestController {

  @ApiOperation(
      value = "서버 연결 확인")
  @ApiResponse(responseCode = "200", description = "연결 확인 성공")
  @GetMapping("/temp")
  public String temporary() {
    return "연결 완료";
  }

}
