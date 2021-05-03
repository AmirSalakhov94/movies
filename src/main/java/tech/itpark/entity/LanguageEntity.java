package tech.itpark.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LanguageEntity {

    private UUID uuid;
    private String iso6391;
    private String name;
}
