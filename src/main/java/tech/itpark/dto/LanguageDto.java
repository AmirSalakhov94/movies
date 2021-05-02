package tech.itpark.dto;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LanguageDto {

    private UUID id;
    @SerializedName("iso_639_1")
    private String iso639;
    private String name;
}
