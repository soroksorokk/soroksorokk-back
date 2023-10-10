package sorok.soroksorok.comment.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;
import sorok.soroksorok.comment.entity.Comment;

@Getter
@Builder
@ApiModel(value = "댓글 조회 응답")
public class CommentRes {

  @ApiModelProperty(value="댓글 고유 id", example = "1", required = true)
  private Long id;

  @ApiModelProperty(value="댓글 작성자 닉네임", example = "닉네임", required = true)
  private String nickname;

  @ApiModelProperty(value="댓글 내용", example = "내용", required = true)
  private String content;

  @ApiModelProperty(value="댓글 작성 시간", example = "", required = true)
  private LocalDateTime createdAt;

  public static CommentRes of(Comment comment) {
    return CommentRes
        .builder()
        .id(comment.getId())
        .content(comment.getContent())
        .nickname(comment.getUser().getNickname())
        .createdAt(comment.getCreatedAt())
        .build();
  }

  public static List<CommentRes> of(Page<Comment> comments) {
    return comments.stream().map(CommentRes::of).collect(Collectors.toList());
  }

}
