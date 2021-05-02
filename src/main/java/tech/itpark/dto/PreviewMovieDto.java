package tech.itpark.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PreviewMovieDto {

    private long id;
    private String originalTitle;
    private String homepage;
    private String posterPath;
}
