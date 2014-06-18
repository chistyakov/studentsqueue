package studentsqueue;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("T")
@Table(name = "TEACHER")
@NamedQueries({
    @NamedQuery(name = "Teacher.getQueuesByTeacher", query = "SELECT q FROM Queue q WHERE q.teacher.id = :teacherId")})
public class Teacher extends User {
}
