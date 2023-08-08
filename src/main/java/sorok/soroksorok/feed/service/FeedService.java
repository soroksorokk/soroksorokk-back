package sorok.soroksorok.feed.service;

import java.io.IOException;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import sorok.soroksorok.feed.dto.FeedEditReq;
import sorok.soroksorok.feed.dto.FeedSearchCond;
import sorok.soroksorok.user.entity.User;
import sorok.soroksorok.feed.dto.FeedReq;
import sorok.soroksorok.feed.dto.FeedRes;
import sorok.soroksorok.feed.entity.Feed;

public interface FeedService {

  void createFeed(MultipartFile image, FeedReq req, User user) throws IOException;

  FeedRes selectFeed(Long id);

  Feed getFeedEntityById(Long id);

  Page<FeedRes> selectFeeds(FeedSearchCond cond);

  void editFeed(Long id, MultipartFile image, FeedEditReq req, User user) throws IOException;

  void deleteFeed(Long id, User user);
}
