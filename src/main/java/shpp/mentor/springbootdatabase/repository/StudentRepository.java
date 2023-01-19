package shpp.mentor.springbootdatabase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shpp.mentor.springbootdatabase.entity.StudentsEntity;

@Repository
public interface StudentRepository extends JpaRepository<StudentsEntity,Long> {

}
