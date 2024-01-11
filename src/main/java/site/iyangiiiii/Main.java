package site.iyangiiiii;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(Main.class);
		builder.headless(false).run(args);
	}
}
