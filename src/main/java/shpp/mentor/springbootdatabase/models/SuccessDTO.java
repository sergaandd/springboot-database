package shpp.mentor.springbootdatabase.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SuccessDTO {
    private String time;
    private int status;
    private String message;

}