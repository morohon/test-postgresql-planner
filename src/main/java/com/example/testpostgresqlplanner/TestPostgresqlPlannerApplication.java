package com.example.testpostgresqlplanner;

import com.example.testpostgresqlplanner.entity.Organization;
import com.example.testpostgresqlplanner.repository.OrganizationsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
@RequiredArgsConstructor
public class TestPostgresqlPlannerApplication implements ApplicationRunner {

    public static final int N_THREADS = 10;
    private final OrganizationsRepository organizationsRepository;
    private final AtomicLong inns = new AtomicLong(1_000_000_000L);
    private final AtomicLong ogrns = new AtomicLong(1_000_000_000_000L);

    public static void main(String[] args) {
        SpringApplication.run(TestPostgresqlPlannerApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<Future> futures = new ArrayList<>();

        for (int i = 0; i <= N_THREADS; i++) {
            futures.add(CompletableFuture.runAsync(this::createOrganization));
        }

        while (true) {
            if (futures.stream().allMatch(Future::isDone)) {
                break;
            }
        }

    }

    private void createOrganization() {

        PodamFactoryImpl podamFactory = new PodamFactoryImpl();
        Random random = new Random();

        for(int i = 0; i <= 10_000_000; i++) {
            Organization organization = new Organization();
            organization.setFullname(podamFactory.manufacturePojo(String.class));
            organization.setShortname(random.nextInt(2) % 2 == 0 ? null : podamFactory.manufacturePojo(String.class));
            organization.setOgrn(String.valueOf(ogrns.getAndIncrement()));
            organization.setInn(String.valueOf(inns.getAndIncrement()));
            organizationsRepository.save(organization);
        }


    }
}
