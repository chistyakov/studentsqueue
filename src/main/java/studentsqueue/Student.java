/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentsqueue;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author teamdevelopment
 */
@Entity
@Table(name = "STUDENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s"),
    @NamedQuery(name = "Student.findByUserId", query = "SELECT s FROM Student s WHERE s.userId = :userId"),
    @NamedQuery(name = "Student.findByGroupName", query = "SELECT s FROM Student s WHERE s.groupName = :groupName")})
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "USER_ID")
    private BigDecimal userId;
    @Size(max = 20)
    @Column(name = "GROUP_NAME")
    private String groupName;
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private User user;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentId")
    private Collection<StudentInQueue> studentInQueueCollection;

    public Student() {
    }

    public Student(BigDecimal userId) {
        this.userId = userId;
    }

    public BigDecimal getUserId() {
        return userId;
    }

    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @XmlTransient
    public User getUser() {
        return user;
    }

    @XmlTransient
    public void setUser(User user) {
        this.user = user;
    }

    @XmlTransient
    public Collection<StudentInQueue> getStudentInQueueCollection() {
        return studentInQueueCollection;
    }

    public void setStudentInQueueCollection(Collection<StudentInQueue> studentInQueueCollection) {
        this.studentInQueueCollection = studentInQueueCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "studentsqueue.Student[ userId=" + userId + " ]";
    }
    
}
