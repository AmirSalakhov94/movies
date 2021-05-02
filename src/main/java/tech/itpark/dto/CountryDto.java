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
public class CountryDto {

    @EqualsAndHashCode.Exclude
    private UUID uuid;
    @SerializedName("iso_3166_1")
    private String iso3166;
    private String name;
}
