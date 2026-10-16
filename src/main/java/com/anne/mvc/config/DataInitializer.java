package com.anne.mvc.config;

import com.anne.mvc.entity.Category;
import com.anne.mvc.entity.Device;
import com.anne.mvc.repository.CategoryRepository;
import com.anne.mvc.repository.DeviceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(CategoryRepository categoryRepo, DeviceRepository deviceRepo) {
        return args -> {
            try {
                if (deviceRepo.count() == 0) {
                    Category c1 = categoryRepo.save(Category.builder().name("Smart Security").build());
                    Category c2 = categoryRepo.save(Category.builder().name("Smart Lighting").build());
                    Category c3 = categoryRepo.save(Category.builder().name("Smart Climate").build());

                    deviceRepo.save(Device.builder()
                            .code("DEV001")
                            .name("Smart Door Lock")
                            .cate(c1)
                            .price(220.0)
                            .warrantyMonths(24)
                            .installationDate(LocalDate.of(2026, 6, 15))
                            .status("Active")
                            .build());

                    deviceRepo.save(Device.builder()
                            .code("DEV002")
                            .name("Smart Motion Sensor")
                            .cate(c1)
                            .price(65.0)
                            .warrantyMonths(12)
                            .installationDate(LocalDate.of(2026, 4, 20))
                            .status("Active")
                            .build());

                    deviceRepo.save(Device.builder()
                            .code("DEV003")
                            .name("Smart LED Strip")
                            .cate(c2)
                            .price(35.0)
                            .warrantyMonths(6)
                            .installationDate(LocalDate.of(2026, 3, 10))
                            .status("Inactive")
                            .build());

                    deviceRepo.save(Device.builder()
                            .code("DEV004")
                            .name("Smart Thermostat")
                            .cate(c3)
                            .price(180.0)
                            .warrantyMonths(18)
                            .installationDate(LocalDate.of(2026, 1, 5))
                            .status("Active")
                            .build());
                }
            } catch (Exception e) {
                // Log and continue if running in an environment without DDL permissions
                System.out.println("DataInitializer skipped: " + e.getMessage());
            }
        };
    }
}
