package shpp.mentor.springbootdatabase.models;

public class Student {

    private final String first_name;
    private final String last_name;

    private final String ipn;


    public Student(String f_name,String l_name,String ipn) {
        first_name = f_name;
        last_name = l_name;
        this.ipn = ipn;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getIpn() {
        return ipn;
    }
}
