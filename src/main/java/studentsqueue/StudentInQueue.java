package studentsqueue;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "STUDENT_IN_QUEUE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentInQueue.findAll", query = "SELECT s FROM StudentInQueue s"),
    @NamedQuery(name = "StudentInQueue.findById", query = "SELECT s FROM StudentInQueue s WHERE s.id = :id"),
    @NamedQuery(name = "StudentInQueue.findByStudentIdAndQueueId", query = "SELECT s FROM StudentInQueue s WHERE s.queue.id = :queueId AND s.student.id = :studentId"),
    @NamedQuery(name = "StudentInQueue.findByStudentIdAndRank", query = "SELECT s FROM StudentInQueue s WHERE s.queue.id = :queueId AND s.rank = :rank"),
    @NamedQuery(name = "StudentInQueue.getStudentRankInQueue", query = "SELECT s.rank FROM StudentInQueue s WHERE s.queue.id = :queueId AND s.student.id = :studentId"),
    @NamedQuery(name = "StudentInQueue.findByDescription", query = "SELECT s FROM StudentInQueue s WHERE s.description = :description")})
public class StudentInQueue implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    // use allocationSize=1 because of this shit: http://stackoverflow.com/a/20267392
    @SequenceGenerator(name = "RANK_GEN", sequenceName = "STUDENT_IN_QUEUE_RANK_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RANK_GEN")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "RANK")
    private BigDecimal rank;
    @Size(max = 20)
    @Column(name = "DESCRIPTION")
    private String description;
    @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Student student;
    @JoinColumn(name = "QUEUE_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Queue queue;

    public StudentInQueue() {
    }

    public StudentInQueue(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getRank() {
        return rank;
    }

    public void setRank(BigDecimal rank) {
        this.rank = rank;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @XmlTransient
    public Queue getQueue() {
        return queue;
    }

    @XmlTransient
    public void setQueue(Queue queue) {
        this.queue = queue;
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
        if (!(object instanceof StudentInQueue)) {
            return false;
        }
        StudentInQueue other = (StudentInQueue) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "studentsqueue.StudentInQueue[ rank=" + id + " ]";
    }
}
