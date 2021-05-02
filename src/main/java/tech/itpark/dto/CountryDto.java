package tech.itpark.dto;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryDto {

    private UUID id;
    @SerializedName("iso_3166_1")
    private String iso3166;
    private String name;
}
