package by.sarnova.monitorsensors.service;

import by.sarnova.monitorsensors.dto.SensorDto;
import by.sarnova.monitorsensors.entity.Sensor;
import by.sarnova.monitorsensors.exeption.CustomException;
import by.sarnova.monitorsensors.filter.SensorFilter;
import by.sarnova.monitorsensors.mapper.SensorMapper;
import by.sarnova.monitorsensors.repository.SensorCriteria;
import by.sarnova.monitorsensors.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SensorService {
    private final SensorRepository repository;
    private final SensorMapper sensorMapper;

    public SensorDto getById(Long id) throws CustomException {
        Sensor sensor = repository.findById(id).orElseThrow(()->new CustomException("no sensor with id="+id+" in database"));
        return sensorMapper.map(sensor);
    }
    public List<SensorDto> findAll(SensorFilter filter){
        Specification<Sensor> specification = SensorCriteria.filterByCriteria(filter);
        return repository.findAll(specification).stream().map(sensorMapper::map).collect(Collectors.toList());
    }

    public SensorDto save(SensorDto sensorDto) throws CustomException{
        Sensor sensor = sensorMapper.map(sensorDto);
        SensorDto result = sensorMapper.map(repository.save(sensor));
        return result;
    }

    public void update(Long id, SensorDto sensorDto) throws CustomException{
        if (repository.existsById(id)){
            Sensor sensor = sensorMapper.map(sensorDto);
            sensor.setId(id);
            repository.update(sensor);
            log.info("Sensor with id={} was updated",id);
        }
        else {
            log.info("Sensor id={} is not in the database",id);
        }
    }

    public void delete(Long id) throws CustomException {
        if (repository.existsById(id)){
            repository.deleteById(id);
            log.info("Sensor with id={} was deleted",id);
        }
        else {
            throw new CustomException("Sensor id="+id+" is not in the database");
        }
    }
}
