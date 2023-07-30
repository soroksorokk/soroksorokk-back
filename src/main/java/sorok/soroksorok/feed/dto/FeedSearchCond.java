package sorok.soroksorok.feed.dto;

import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sorok.soroksorok.feed.entity.Mood;

@ApiModel(value = "피드 검색 조건")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FeedSearchCond {

  private Integer page;

  private SortBy sortBy;

  private Mood mood;

}
