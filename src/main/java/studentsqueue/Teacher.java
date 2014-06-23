package studentsqueue;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@DiscriminatorValue("T")
@Table(name = "TEACHER")
@NamedQueries({
    @NamedQuery(name = "Teacher.getQueuesByTeacher", query = "SELECT q FROM Queue q WHERE q.teacher.id = :teacherId"),
    @NamedQuery(name = "Teacher.startProcessQueue", query = "UPDATE Queue q SET q.inProcess = 'Y' WHERE q.id = :queueId"),
    @NamedQuery(name = "Teacher.pauseProcessQueue", query = "UPDATE Queue q SET q.inProcess = 'N' WHERE q.id = :queueId")
})
@XmlRootElement
public class Teacher extends User {
}
