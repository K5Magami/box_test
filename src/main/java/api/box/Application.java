package api.box;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// `gradle bootRun`でアプリケーション実行
// `gradle build`でjarの作成
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}