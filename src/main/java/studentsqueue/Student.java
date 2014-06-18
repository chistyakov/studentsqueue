package studentsqueue;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@DiscriminatorValue("S")
@Table(name = "STUDENT")
@NamedQueries({
    @NamedQuery(name = "Student.findAll", query = "SELECT q FROM Student q"),
    @NamedQuery(name = "Student.findById", query = "SELECT q FROM Student q WHERE q.id = :id"),
    @NamedQuery(name = "Student.findByUsername", query = "SELECT q FROM Student q WHERE q.username = :username"),
    @NamedQuery(name = "Student.findByPasswordHash", query = "SELECT q FROM Student q WHERE q.passwordHash = :passwordHash"),
    @NamedQuery(name = "Student.findByRealName", query = "SELECT q FROM Student q WHERE q.realName = :realName"),
    @NamedQuery(name = "Student.getQueuesByStudent", query = "SELECT q FROM Queue q INNER JOIN q.studentInQueueList sq WHERE sq.student.id = :studentId")})
public class Student extends User {
    @Column(name = "GROUP_NAME")
    private String groupName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="student")
    private List<StudentInQueue> studentInQueueList;
    
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    
    @XmlTransient
    public List<StudentInQueue> getStudentInQueueList() {
        return studentInQueueList;
    }

    @XmlTransient
    public void setStudentInQueueList(List<StudentInQueue> studentInQueueList) {
        this.studentInQueueList = studentInQueueList;
    }

}