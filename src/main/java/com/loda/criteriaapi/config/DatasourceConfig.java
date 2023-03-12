package com.loda.criteriaapi.config;

import com.loda.criteriaapi.entity.User;
import com.loda.criteriaapi.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import com.loda.criteriaapi.entity.User.UserType;

@Configuration
@RequiredArgsConstructor
public class DatasourceConfig {
    // inject bởi RequiredArgsConstructor
    private final UserRepository userRepository;

    @PostConstruct
    public void initData() {
        // Insert 100 User vào H2 Database sau khi
        // DatasourceConfig được khởi tạo
        final Random r = new Random();
        userRepository.saveAll(IntStream.range(0, 100)
                .mapToObj(i -> User.builder()
                        .name("name-" + i)
                        .type(r.nextDouble() >= 0.5 ? UserType.VIP : UserType.NORMAL)
                        .build())
                .collect(Collectors.toList())
        );
    }
}