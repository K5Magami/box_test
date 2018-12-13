package api.box;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import api.box.storage.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageService.class)
// `gradle bootRun`でアプリケーション実行
// `gradle build`でjarの作成
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    CommandLineRunner init(StorageService storageService) {
    	return(args) -> {
    		storageService.deleteAll();
    		storageService.init();
    	};
    }
}