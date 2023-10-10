package sorok.soroksorok.scrap.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sorok.soroksorok.feed.entity.Feed;
import sorok.soroksorok.feed.service.FeedService;
import sorok.soroksorok.scrap.entity.Scrap;
import sorok.soroksorok.scrap.repository.ScrapRepository;
import sorok.soroksorok.user.entity.User;

@Service
@RequiredArgsConstructor
public class ScrapService {

  private final ScrapRepository scrapRepository;

  private final FeedService feedService;

  @Transactional
  public void scrapFeed(Long feedId, User user) {
    Feed feed = feedService.getFeedEntityById(feedId);

    Scrap scrap = Scrap.builder().feed(feed).user(user).build();

    scrapRepository.save(scrap);
  }

  @Transactional
  public void unscrapFeed(Long feedId, User user) {
    Scrap scrap = scrapRepository.findByFeed_IdAndUser_Id(feedId, user.getId())
        .orElseThrow(RuntimeException::new);
    scrapRepository.delete(scrap);
  }

  @Transactional(readOnly = true)
  public Long getFeedScrapCount(Long feedId) {
    return scrapRepository.countByFeed_Id(feedId);
  }

}
