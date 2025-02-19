package by.sarnova.monitorsensors.exeption;

import lombok.Data;

import java.util.Date;

@Data
public class SensorsException {
    private int status;
    private String message;
    private Date timestamp;

    public SensorsException(int status, String message){
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }
}
