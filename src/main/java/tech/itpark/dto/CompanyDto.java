package tech.itpark.dto;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {

    private UUID id;
    @SerializedName("id")
    private Long idWithFile;
    private String name;
}
