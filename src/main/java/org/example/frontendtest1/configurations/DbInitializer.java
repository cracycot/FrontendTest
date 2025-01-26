package org.example.frontendtest1.configurations;


import org.example.frontendtest1.models.HistoricalBuilding;
import org.example.frontendtest1.repositories.HistoricalBuildingRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbInitializer {

    @Bean
    public CommandLineRunner initDatabase(HistoricalBuildingRepository repository) {
        return args -> {
            repository.save(new HistoricalBuilding(
                    "Колизей",
                    "Рим",
                    80,
                    "Один из крупнейших памятников Древнего Рима."
            ));
            repository.save(new HistoricalBuilding(
                    "Тадж-Махал",
                    "Агра",
                    1643,
                    "Мавзолей-мечеть, построенная императором Шах-Джаханом."
            ));
            repository.save(new HistoricalBuilding(
                    "Собор Василия Блаженного",
                    "Москва",
                    1561,
                    "Православный храм на Красной площади, памятник русской архитектуры."
            ));
            repository.save(new HistoricalBuilding(
                    "Пизанская башня",
                    "Пиза",
                    1372,
                    "Колокольня, известная наклоном."
            ));
        };
    }
}