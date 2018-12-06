import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DataSyncServiceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DataSyncServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
