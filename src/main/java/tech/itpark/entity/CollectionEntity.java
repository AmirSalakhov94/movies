package tech.itpark.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollectionEntity {

    private UUID uuid;
    private long idWithFile;
    private String name;
    private String posterPath;
    private String backdropPath;
}
