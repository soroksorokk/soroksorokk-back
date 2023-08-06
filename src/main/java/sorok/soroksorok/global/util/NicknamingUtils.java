package sorok.soroksorok.global.util;

import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NicknamingUtils {

  @Value("#{'${nickname.naming.prefix}'.split(', ')}")
  private List<String> prefix;

  @Value("#{'${nickname.naming.suffix}'.split(', ')}")
  private List<String> suffix;

  public String makeRandomNaming() {
    String pre = this.prefix.get((int) (Math.random() * this.prefix.size()));
    String suf = this.suffix.get((int) (Math.random() * this.suffix.size()));
    int num = (int) (Math.random() * 100);

    StringBuilder sb = new StringBuilder();
    sb.append(pre);
    sb.append(suf);
    sb.append(num);

    return sb.toString();
  }

}
