package sorok.soroksorok.feed.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sorok.soroksorok.feed.dto.FeedEditReq;
import sorok.soroksorok.feed.dto.FeedPagingRes;
import sorok.soroksorok.feed.entity.Mood;
import sorok.soroksorok.feed.entity.Tags;
import sorok.soroksorok.feed.entity.TempFeed;
import sorok.soroksorok.feed.repository.TagsRepository;
import sorok.soroksorok.feed.repository.TempFeedRepository;
import sorok.soroksorok.user.entity.User;
import sorok.soroksorok.feed.dto.FeedReq;
import sorok.soroksorok.feed.dto.FeedRes;
import sorok.soroksorok.feed.entity.Feed;
import sorok.soroksorok.feed.repository.FeedRepository;
import sorok.soroksorok.global.s3.S3Upload;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {

  private final FeedRepository feedRepository;
  private final TempFeedRepository tempFeedRepository;
  private final TagsRepository tagsRepository;
  private final S3Upload s3Upload;

  @Override
  @Transactional
  public void createFeed(MultipartFile image, FeedReq req, User user) throws IOException {
    String imageUrl = "";
    if(image == null) {
      imageUrl = "none";
    } else {
      imageUrl = s3Upload.upload(image);
    }
    Feed feed = req.toEntity(imageUrl, user);
    List<Tags> tags = req.getTags().stream()
        .map(x -> Tags.builder().content(x).feed(feed).build()).collect(Collectors.toList());
    feedRepository.saveAndFlush(feed);
    tagsRepository.saveAllAndFlush(tags);
  }

  @Override
  @Transactional(readOnly = true)
  public FeedRes selectFeed(Long id) {
    Feed feed = getFeedEntityById(id);
    return FeedRes.of(feed);
  }

  @Override
  @Transactional
  public void editFeed(Long id, MultipartFile image, FeedEditReq req, User user) throws IOException {
    Feed feed = getFeedEntityById(id);
    validateIfUserWroteFeed(feed, user);
    String imageUrl = s3Upload.upload(image);

    tagsRepository.deleteByFeedAllIgnoreCase(feed);

    List<Tags> tags = req.getTags().stream()
        .map(x -> Tags.builder().content(x).feed(feed).build()).collect(Collectors.toList());

    tagsRepository.saveAllAndFlush(tags);

    feed.editFeed(req, imageUrl, tags);
  }

  @Override
  @Transactional
  public void deleteFeed(Long id, User user) {
    Feed feed = getFeedEntityById(id);
    validateIfUserWroteFeed(feed, user);
    feedRepository.delete(feed);
  }

  @Override
  @Transactional
  public List<FeedPagingRes> selectFeedsByNickname(String nickname) {
    List<Feed> feeds = feedRepository.findByUser_NicknameOrderByCreatedAtDesc(nickname);
    return feeds.stream().map(FeedPagingRes::of).collect(Collectors.toList());
  }

  @Override
  @Transactional
  public void saveFeedTemporarily(MultipartFile image, FeedReq req, User user) throws IOException {
    String imageUrl = s3Upload.upload(image);
    TempFeed tempFeed = req.toTempEntity(imageUrl, user);
    tempFeedRepository.deleteByUser(user);
    tempFeedRepository.save(tempFeed);
  }

  @Override
  @Transactional
  public FeedRes getTemporaryFeed(User user) {
    TempFeed tempFeed = tempFeedRepository.findByUser(user).orElseThrow(RuntimeException::new);
    return FeedRes.of(tempFeed);
  }

  @Override
  @Transactional
  public Page<FeedPagingRes> selectFeeds(Mood mood, Integer page) {
    PageRequest pageRequest = PageRequest.of(page - 1, 10);

    Page<Feed> feeds;

    if (mood != null) {
      feeds = feedRepository.findByMoodOrderByCreatedAtDesc(mood, pageRequest);
    } else {
      feeds = feedRepository.findByIdGreaterThanEqualOrderByCreatedAtDesc(1L, pageRequest);
    }

    return feeds.map(FeedPagingRes::of);
  }

  @Override
  @Transactional(readOnly = true)
  public Feed getFeedEntityById(Long id) {
    return feedRepository.findById(id).orElseThrow(RuntimeException::new);
  }

  private void validateIfUserWroteFeed(Feed feed, User user) {
    if(!feed.isWroteByUser(user)) {
      throw new RuntimeException();
    }
  }

}
