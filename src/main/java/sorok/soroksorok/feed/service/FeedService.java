package sorok.soroksorok.feed.service;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import sorok.soroksorok.user.entity.User;
import sorok.soroksorok.feed.dto.FeedReq;
import sorok.soroksorok.feed.dto.FeedRes;
import sorok.soroksorok.feed.entity.Feed;

public interface FeedService {

  void createFeed(MultipartFile image, FeedReq req, User user) throws IOException;

  FeedRes selectFeed(Long id);

  Feed getFeedEntityById(Long id);
}
