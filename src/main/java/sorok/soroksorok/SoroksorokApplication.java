package sorok.soroksorok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SoroksorokApplication {

  public static void main(String[] args) {
    SpringApplication.run(SoroksorokApplication.class, args);
  }

}
