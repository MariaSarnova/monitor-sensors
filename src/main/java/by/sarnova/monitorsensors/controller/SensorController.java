package by.sarnova.monitorsensors.controller;

import by.sarnova.monitorsensors.dto.SensorDto;
import by.sarnova.monitorsensors.entity.Sensor;
import by.sarnova.monitorsensors.exeption.CustomException;
import by.sarnova.monitorsensors.filter.SensorFilter;
import by.sarnova.monitorsensors.service.SensorService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sensor")
@RequiredArgsConstructor
public class SensorController {
    private final SensorService service;

    @GetMapping("/list")
    public ResponseEntity<List<SensorDto>> getAllSensorsByCriteria(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String model){
        SensorFilter filter = new SensorFilter(name, model);
        List<SensorDto> sensorDtos = service.findAll(filter);
        return ResponseEntity.ok().body(sensorDtos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<SensorDto> getSensor(@PathVariable Long id) throws CustomException {
        SensorDto sensor = service.getById(id);
        return ResponseEntity.ok(sensor);
    }
    @PostMapping
    public ResponseEntity saveSensor(@Validated @RequestBody SensorDto sensorDto) throws CustomException {
        SensorDto created = service.save(sensorDto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateSensor(@PathVariable Long id,
                                       @Validated @RequestBody SensorDto sensorDto) throws CustomException {
        service.update(id,sensorDto);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteSensor(@PathVariable Long id) throws CustomException {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
