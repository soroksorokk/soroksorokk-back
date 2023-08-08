package sorok.soroksorok.feed.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;

@Getter
@ApiModel(value = "피드 수정 요청")
public class FeedEditReq {

  private String title;

  private String content;

}
