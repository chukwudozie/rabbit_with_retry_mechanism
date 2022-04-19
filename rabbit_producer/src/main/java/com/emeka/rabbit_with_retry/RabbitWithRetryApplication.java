package com.emeka.rabbit_with_retry;

import com.emeka.rabbit_with_retry.entity.Employee;
import com.emeka.rabbit_with_retry.entity.Picture;
import com.emeka.rabbit_with_retry.producer.RetryEmployeeProducer;
import com.emeka.rabbit_with_retry.producer.RetryPictureProducer;
import com.emeka.rabbit_with_retry.producer.SpringPictureProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class RabbitWithRetryApplication  implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RabbitWithRetryApplication.class, args);
    }

    @Autowired
//    private RetryPictureProducer producer;
//    private RetryEmployeeProducer producer;
    private SpringPictureProducer producer;

    private final List<String> SOURCES = List.of("mobile", "web");
    private final List<String> TYPES = List.of("jpg","png", "svg");

    @Override
    public void run(String... args) throws Exception {
        for(int i = 0 ; i < 1; i++){
            Picture picture = new Picture();
            picture.setName("Test Spring--"+i);
            picture.setSize(ThreadLocalRandom.current().nextLong(9001,10000));
            picture.setSource(SOURCES.get(i % SOURCES.size()));
            picture.setType(TYPES.get(i % TYPES.size()));
            producer.sendMessage(picture);

            // publish ten invalid employees
//            Employee employee = new Employee("emp-"+i,null, LocalDate.now());
//            producer.sendMessage(employee);
        }

    }
}
