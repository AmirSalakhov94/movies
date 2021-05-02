package tech.itpark.dto;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenreDto {

    @EqualsAndHashCode.Exclude
    private UUID uuid;
    @SerializedName("id")
    private Long idWithFile;
    private String name;
}
