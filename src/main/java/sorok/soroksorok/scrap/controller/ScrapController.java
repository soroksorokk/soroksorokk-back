package sorok.soroksorok.scrap.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sorok.soroksorok.global.login.UserDetailsImpl;
import sorok.soroksorok.scrap.service.ScrapService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/scraps")
@RequiredArgsConstructor
public class ScrapController {

  private final ScrapService scrapService;

  @PostMapping("/{feedId}")
  public void scrapFeed(
      @PathVariable Long feedId,
      @ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails
  ) {
    scrapService.scrapFeed(feedId, userDetails.getUser());
  }

  @DeleteMapping("/{feedId}")
  public void unscrapFeed(
      @PathVariable Long feedId,
      @ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails
  ) {
    scrapService.unscrapFeed(feedId, userDetails.getUser());
  }

  @GetMapping("/{feedId}")
  public Long getFeedScrapCount(
      @PathVariable Long feedId
  ) {
    return scrapService.getFeedScrapCount(feedId);
  }

}
