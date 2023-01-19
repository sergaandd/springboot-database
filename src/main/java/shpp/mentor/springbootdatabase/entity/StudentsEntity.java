package shpp.mentor.springbootdatabase.entity;

import lombok.NoArgsConstructor;
import lombok.Setter;
import shpp.mentor.springbootdatabase.TestIpnValid;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Setter
@NoArgsConstructor

@Table(name = "students", schema = "public", catalog = "mentor")
public class StudentsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String first_name ;

    @Column(name = "last_name")
    private String last_name ;

    @TestIpnValid(message = "Size of ipn must be 10 digits.")
    @Column(name = "ipn")
    private String ipn ;



    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public String getIpn() {
        return ipn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentsEntity that = (StudentsEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(first_name, that.first_name)
                && Objects.equals(last_name, that.last_name)
                && Objects.equals(ipn, that.ipn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first_name, last_name, ipn);
    }

}
