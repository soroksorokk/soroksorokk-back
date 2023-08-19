package sorok.soroksorok.comment.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;
import sorok.soroksorok.comment.entity.Comment;
import sorok.soroksorok.comment.entity.Reply;

@Builder
@Getter
public class ReplyRes {

  private String nickname;

  private String content;

  private LocalDateTime createdAt;

  public static ReplyRes of(Reply reply) {
    return ReplyRes
        .builder()
        .content(reply.getContent())
        .nickname(reply.getUser().getNickname())
        .createdAt(reply.getCreatedAt())
        .build();
  }

  public static List<ReplyRes> of(Page<Reply> comments) {
    return comments.stream().map(ReplyRes::of).collect(Collectors.toList());
  }

}
