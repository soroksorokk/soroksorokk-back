package sorok.soroksorok.comment.dto;

import io.swagger.annotations.ApiModel;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;
import sorok.soroksorok.comment.entity.Comment;

@ApiModel(value = "댓글 조회 응답")
@Getter
@Builder
public class CommentRes {

  private String nickname;

  private String content;

  private LocalDateTime createdAt;

  public static CommentRes of(Comment comment) {
    return CommentRes
        .builder()
        .content(comment.getContent())
        .nickname(comment.getUser().getNickname())
        .createdAt(comment.getCreatedAt())
        .build();
  }

  public static List<CommentRes> of(Page<Comment> comments) {
    return comments.stream().map(CommentRes::of).collect(Collectors.toList());
  }

}
