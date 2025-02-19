package by.sarnova.monitorsensors.repository;

import by.sarnova.monitorsensors.entity.Sensor;
import by.sarnova.monitorsensors.filter.SensorFilter;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SensorCriteria {
    public static Specification<Sensor> filterByCriteria(SensorFilter filter){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filter.name()!=null && !filter.name().isBlank()){
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),"%"+filter.name().toLowerCase()+"%"));
            }
            if (filter.model()!=null && !filter.model().isBlank()){
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("model")),"%"+filter.model().toLowerCase()+"%"));
            }
            System.out.println("filter.model() "+filter.model()+ " filter.name(): "+filter.name());
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
