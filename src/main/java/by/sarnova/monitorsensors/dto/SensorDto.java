package by.sarnova.monitorsensors.dto;
import by.sarnova.monitorsensors.validator.Range;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensorDto {
    @NotBlank
    @Size(min = 3, max = 30,message = "name must be between 3 and 30 characters")
    private String name;
    @NotBlank
    @Size(max = 15,message = "model must be less or equal to 15 characters")
    private String model;
    @NotNull
    @Range
    private RangeDto range;
    @NotBlank
    private String type;
    @NotBlank
    private String unit;
    @NotBlank
    @Size(max = 40,message = "location must be less or equal to 40 characters")
    private String location;
    @NotBlank
    @Size(max = 200,message = "description must be less or equal to 200 characters")
    private String description;
}
