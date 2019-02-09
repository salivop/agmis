package com.vaitkevicius.agmis.city.data.db;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * *
 * Created By Povilas 08/02/2019
 * *
 **/
@Document(collection = "cities")
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class City {

    @Id
    private String id;

    private String cityName;

    private String minTemperature;
    private String maxTemperature;
    private String weatherCondition;

    private String area;
    private String population;

    private String pressure;
    private String humidity;
}
