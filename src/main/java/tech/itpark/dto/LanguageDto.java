package tech.itpark.dto;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LanguageDto {

    @EqualsAndHashCode.Exclude
    private UUID uuid;
    @SerializedName("iso_639_1")
    private String iso6391;
    private String name;
}
