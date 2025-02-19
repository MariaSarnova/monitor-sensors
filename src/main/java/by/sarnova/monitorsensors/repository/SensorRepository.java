package by.sarnova.monitorsensors.repository;

import by.sarnova.monitorsensors.entity.Sensor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Optional;

public interface SensorRepository extends JpaRepository<Sensor, Long>,JpaSpecificationExecutor<Sensor> {
    @Modifying
    @Query("""
    update Sensor s set s.name = :#{#sensor.name},
                s.model = :#{#sensor.model},
                s.type = :#{#sensor.type},
                s.from = :#{#sensor.from},
                s.to = :#{#sensor.to},
                s.unit = :#{#sensor.unit},
                s.location = :#{#sensor.location},
                s.description = :#{#sensor.description}
                where s.id = :#{#sensor.id}
""")
    void update(@Param("sensor") Sensor sensor);
    @EntityGraph(value = "Sensor.withTypeAndUnit", type = EntityGraph.EntityGraphType.FETCH)
    List<Sensor> findAll(@Param("sensor") Specification<Sensor> spec);
    @EntityGraph(value = "Sensor.withTypeAndUnit", type = EntityGraph.EntityGraphType.FETCH)
    Optional<Sensor> findById(@Param("id")Long id);
}
