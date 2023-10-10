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
import sorok.soroksorok.comment.entity.Reply;

@Builder
@Getter
@ApiModel(value = "댓글 조회 응답")
public class ReplyRes {

  @ApiModelProperty(value="대댓글 고유 id", example = "1", required = true)
  private Long id;

  @ApiModelProperty(value="대댓글 작성자 닉네임", example = "닉네임", required = true)
  private String nickname;

  @ApiModelProperty(value="대댓글 내용", example = "닉네임", required = true)
  private String content;

  @ApiModelProperty(value="대댓글 작성 시간", example = "닉네임", required = true)
  private LocalDateTime createdAt;

  public static ReplyRes of(Reply reply) {
    return ReplyRes
        .builder()
        .id(reply.getId())
        .content(reply.getContent())
        .nickname(reply.getUser().getNickname())
        .createdAt(reply.getCreatedAt())
        .build();
  }

  public static List<ReplyRes> of(Page<Reply> comments) {
    return comments.stream().map(ReplyRes::of).collect(Collectors.toList());
  }

}
