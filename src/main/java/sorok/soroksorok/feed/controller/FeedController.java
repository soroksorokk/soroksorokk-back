package sorok.soroksorok.feed.controller;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sorok.soroksorok.feed.dto.FeedEditReq;
import sorok.soroksorok.feed.dto.FeedReq;
import sorok.soroksorok.feed.dto.FeedRes;
import sorok.soroksorok.feed.dto.FeedSearchCond;
import sorok.soroksorok.feed.service.FeedService;
import sorok.soroksorok.global.login.UserDetailsImpl;

@Slf4j
@RestController
@RequestMapping("/api/feeds")
@RequiredArgsConstructor
public class FeedController {

  private final FeedService feedService;

  @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
  public void createFeed(
      @RequestPart(required = false) MultipartFile image,
      @RequestPart FeedReq req,
      @AuthenticationPrincipal UserDetailsImpl userDetails
  ) throws IOException {
    feedService.createFeed(image, req, userDetails.getUser());
  }

  //
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public FeedRes selectFeed(
      @PathVariable Long id
  ) {
    return feedService.selectFeed(id);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public void selectFeeds(
      @RequestParam FeedSearchCond cond
  ) {

  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void editFeed(
      @PathVariable Long id,
      @RequestPart(required = false) MultipartFile image,
      @RequestPart FeedEditReq req,
      @AuthenticationPrincipal UserDetailsImpl userDetails
  ) {

  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteFeed(
      @PathVariable Long id,
      @AuthenticationPrincipal UserDetailsImpl userDetails
  ) {

  }

}
