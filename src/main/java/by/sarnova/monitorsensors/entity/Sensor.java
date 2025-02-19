package by.sarnova.monitorsensors.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(schema = "public")
@NamedEntityGraph(name = "Sensor.withTypeAndUnit",
        attributeNodes = {@NamedAttributeNode("type"),@NamedAttributeNode("unit")})
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 3, max = 30,message = "name must be between 3 and 30 characters")
    private String name;
    @NotNull
    @Size(max = 15,message = "model must be less or equal to 15 characters")
    private String model;
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "type_id")
    private Type type;
    @NotNull
    @Column(name="from_r")
    private Integer from;
    @NotNull
    @Column(name="to_r")
    private Integer to;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    @NotNull
    private Unit unit;
    @NotNull
    @Size(max = 40,message = "location must be less or equal to 40 characters")
    private String location;
    @NotNull
    @Size(max = 200,message = "description must be less or equal to 200 characters")
    private String description;

}
