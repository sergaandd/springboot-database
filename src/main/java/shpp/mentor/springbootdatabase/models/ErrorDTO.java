package shpp.mentor.springbootdatabase.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorDTO {
    private String time;
    private int status;
    private String error;
    private String message;

}
