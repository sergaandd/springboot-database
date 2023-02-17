package shpp.mentor.springbootdatabase.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import shpp.mentor.springbootdatabase.controllers.Status;
import shpp.mentor.springbootdatabase.models.CheckEnum;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Setter
@NoArgsConstructor

@Table(name = "tasks", schema = "public", catalog = "mentor")
public class TaskEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @JsonProperty(value = "name")
    @Column(name = "name")
    @Length(min = 3,max=25)
    private String name;

    @JsonProperty(value = "start")
    @Column(name = "start")
    private LocalDateTime start;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    @CheckEnum()
    private Status status = Status.startStatus();

    public TaskEntity(String name, LocalDateTime start) {
        this.name = name;
        this.start = start;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public String getStatus() {
        return status.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskEntity that = (TaskEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name)
                && Objects.equals(start, that.start)
                && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, start, status);
    }

}
