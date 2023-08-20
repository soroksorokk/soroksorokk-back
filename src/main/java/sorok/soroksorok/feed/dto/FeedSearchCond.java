package sorok.soroksorok.feed.dto;

import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sorok.soroksorok.feed.entity.Mood;

@Builder
@ApiModel(value = "피드 검색 조건")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class FeedSearchCond {

  private Integer page;

  private SortBy sortBy;

  private Mood mood;

  public static FeedSearchCond of(Integer page, String sortBy, Mood mood) {
    return FeedSearchCond
        .builder()
        .page(page)
        .sortBy(sortBy.equals("NEW") ? SortBy.NEW : SortBy.ETC)
        .mood(mood)
        .build();
  }

}
