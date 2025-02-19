package by.sarnova.monitorsensors.mapper;

import by.sarnova.monitorsensors.dto.RangeDto;
import by.sarnova.monitorsensors.dto.SensorDto;
import by.sarnova.monitorsensors.entity.Sensor;
import by.sarnova.monitorsensors.entity.Type;
import by.sarnova.monitorsensors.entity.Unit;
import by.sarnova.monitorsensors.exeption.CustomException;
import by.sarnova.monitorsensors.repository.TypeRepository;
import by.sarnova.monitorsensors.repository.UnitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class SensorMapper {
    private final TypeRepository typeRepo;
    private final UnitRepository unitRepo;
    public Sensor map(SensorDto obj) throws CustomException {
        Sensor sensor = new Sensor();
        sensor.setName(obj.getName());
        sensor.setModel(obj.getModel());
        RangeDto rangeDto = obj.getRange();
        sensor.setFrom(rangeDto.getFrom());
        sensor.setTo(rangeDto.getTo());
        sensor.setType(getType(obj.getType()));
        sensor.setUnit(getUnit(obj.getUnit()));
        sensor.setLocation(obj.getLocation());
        sensor.setDescription(obj.getDescription());
        return sensor;
    }

    public SensorDto map(Sensor obj) {
        return new SensorDto(obj.getName(),
                obj.getModel(),
                new RangeDto(obj.getFrom(),obj.getTo()),
                obj.getType().getName(),
                obj.getUnit().getName(),
                obj.getLocation(),
                obj.getDescription());
    }

    public Unit getUnit(String name) throws CustomException {
        return Optional.ofNullable(name).flatMap(unitRepo::findByName)
                .orElseThrow(()->new CustomException("no unit with name="+name));
    }

    public Type getType(String name) throws CustomException {
        return Optional.ofNullable(name).flatMap(typeRepo::findByName)
                .orElseThrow(()->new CustomException("no unit with name="+name));
    }
}
