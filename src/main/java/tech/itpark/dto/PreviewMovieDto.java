package tech.itpark.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PreviewMovieDto {

    private UUID uuid;
    private String originalTitle;
    private String homepage;
    private String posterPath;
    private float voteAverage;
    private long voteCount;
}
