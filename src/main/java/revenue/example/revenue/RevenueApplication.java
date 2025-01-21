package revenue.example.revenue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class RevenueApplication {

	public static void main(String[] args) {
		SpringApplication.run(RevenueApplication.class, args);
	}

}
