package studentsqueue;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("S")
@Table(name = "STUDENT")
@NamedQueries({
    @NamedQuery(name = "Student.findAll", query = "SELECT q FROM Student q"),
    @NamedQuery(name = "Student.findById", query = "SELECT q FROM Student q WHERE q.id = :id"),
    @NamedQuery(name = "Student.findByUsername", query = "SELECT q FROM Student q WHERE q.username = :username"),
    @NamedQuery(name = "Student.findByPasswordHash", query = "SELECT q FROM Student q WHERE q.passwordHash = :passwordHash"),
    @NamedQuery(name = "Student.findByRealName", query = "SELECT q FROM Student q WHERE q.realName = :realName")})
public class Student extends User {
    @Column(name = "GROUP_NAME")
    private String groupName;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}