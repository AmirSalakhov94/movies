package tech.itpark.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PreviewMovieEntity {

    private UUID uuid;
    private String originalTitle;
    private String homepage;
    private String posterPath;
    private String genre;
    private Float voteAverage;
    private Long voteCount;
}
