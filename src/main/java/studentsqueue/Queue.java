package studentsqueue;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "QUEUE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Queue.findAll", query = "SELECT q FROM Queue q"),
    @NamedQuery(name = "Queue.findById", query = "SELECT q FROM Queue q WHERE q.id = :id"),
    @NamedQuery(name = "Queue.findByName", query = "SELECT q FROM Queue q WHERE q.name = :name"),
    @NamedQuery(name = "Queue.findByInProcess", query = "SELECT q FROM Queue q WHERE q.inProcess = :inProcess")})
public class Queue implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name="QUEUES_ID_GEN", sequenceName="QUEUES_SEQ", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="QUEUES_ID_GEN")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NAME")
    private String name;
    @Column(name = "IN_PROCESS")
    private Character inProcess = 'N';
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "queue")
    @OrderColumn(name="RANK")
    private List<StudentInQueue> studentInQueueList;
    @NotNull
    @JoinColumn(name = "TEACHER_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Teacher teacher;

    public Queue() {
    }

    public Queue(BigDecimal id) {
        this.id = id;
    }

    public Queue(BigDecimal id, String name) {
        this.id = id;
        this.name = name;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character getInProcess() {
        return inProcess;
    }

    public void setInProcess(Character inProcess) {
        this.inProcess = inProcess;
    }

    public List<StudentInQueue> getStudentInQueueList() {
        return studentInQueueList;
    }

    public void setStudentInQueueList(List<StudentInQueue> studentInQueueList) {
        this.studentInQueueList = studentInQueueList;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Queue)) {
            return false;
        }
        Queue other = (Queue) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "studentsqueue.Queue[ id=" + id + " ]";
    }
}
