package com.vaitkevicius.agmis.weatherMap.data.db;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * *
 * Created By Povilas 09/02/2019
 * *
 **/
@Document(collection = "weathermaps")
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeatherMap {

    @Id
    private String id;

    private String name;
    private String country;
    private List<String> coord;
}
