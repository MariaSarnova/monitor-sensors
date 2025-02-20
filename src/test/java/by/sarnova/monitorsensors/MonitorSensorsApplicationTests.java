package by.sarnova.monitorsensors;

import by.sarnova.monitorsensors.repository.SensorRepository;
import by.sarnova.monitorsensors.repository.TypeRepository;
import by.sarnova.monitorsensors.repository.UnitRepository;
import by.sarnova.monitorsensors.service.SensorService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
class MonitorSensorsApplicationTests {

    @Test
    void contextLoads() {
    }

}
