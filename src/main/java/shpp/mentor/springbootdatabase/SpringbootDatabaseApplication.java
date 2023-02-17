package shpp.mentor.springbootdatabase;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import shpp.mentor.springbootdatabase.entity.TaskEntity;
import shpp.mentor.springbootdatabase.repository.TaskRepository;

import java.time.LocalDateTime;

@SpringBootApplication
public class SpringbootDatabaseApplication {
    public static void main(String[] args) {

        SpringApplication.run(SpringbootDatabaseApplication.class, args);
    }


    @Bean
    public CommandLineRunner insertNew(TaskRepository taskRepository) {
        return args -> {
            taskRepository.save(new TaskEntity("WakeUp", LocalDateTime.now().plusMinutes(5)));
            taskRepository.save(new TaskEntity("Shower", LocalDateTime.now().plusMinutes(15)));
            taskRepository.save(new TaskEntity("Breakfast", LocalDateTime.now().plusMinutes(25)));
            taskRepository.save(new TaskEntity("Dressing", LocalDateTime.now().plusMinutes(35)));
        };
    }
}
