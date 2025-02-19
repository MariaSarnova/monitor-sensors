package by.sarnova.monitorsensors.dto;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RangeDto {
    @Positive(message = "from must be a positive number which is less than to")
    private Integer from;
    @Positive(message = "to must be a positive number which is greater than from")
    private Integer to;

}
