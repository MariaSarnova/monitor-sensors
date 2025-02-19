package by.sarnova.monitorsensors.validator;

import by.sarnova.monitorsensors.dto.RangeDto;
import by.sarnova.monitorsensors.dto.SensorDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RangeValidator implements ConstraintValidator<Range, RangeDto> {

    @Override
    public boolean isValid(RangeDto rangeDto, ConstraintValidatorContext constraintValidatorContext) {
        return rangeDto.getFrom()!=null && rangeDto.getTo()!=null && rangeDto.getFrom()< rangeDto.getTo();
    }
}
