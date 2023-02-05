package shpp.mentor.springbootdatabase.models;

public class ApiErrorDTO {
        private final String code;

        private final String error;

        private final String message;

    public ApiErrorDTO(String code,String error,String message){
            this.code=code;
            this.error=error;
            this.message=message;
        }
    public String toSuccess() {
        return "Success{" +
                "code:'" + code + '\'' +
                ", status:'" + error + '\'' +
                ", message:'" + message + '\'' +
                '}';
    }

    @Override
    public String toString() {
        return "Error{" +
                "code:'" + code + '\'' +
                ", error:'" + error + '\'' +
                ", message:'" + message + '\'' +
                '}';
    }
}
