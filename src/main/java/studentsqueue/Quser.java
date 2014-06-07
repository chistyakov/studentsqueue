/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentsqueue;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author teamdevelopment
 */
@Entity
@Table(name = "QUSER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Quser.findAll", query = "SELECT q FROM Quser q"),
    @NamedQuery(name = "Quser.findById", query = "SELECT q FROM Quser q WHERE q.id = :id"),
    @NamedQuery(name = "Quser.findByUsername", query = "SELECT q FROM Quser q WHERE q.username = :username"),
    @NamedQuery(name = "Quser.findByPasswordHash", query = "SELECT q FROM Quser q WHERE q.passwordHash = :passwordHash"),
    @NamedQuery(name = "Quser.findByRealName", query = "SELECT q FROM Quser q WHERE q.realName = :realName")})
public class Quser implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    // use allocationSize=1 because of this shit: http://stackoverflow.com/a/20267392
    @SequenceGenerator(name="QUSER_ID_GEN", sequenceName="USERS_SEQ", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="QUSER_ID_GEN")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PASSWORD_HASH")
    private String passwordHash;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "REAL_NAME")
    private String realName;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "quser")
    private Student student;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "quser")
    private Teacher teacher;

    public Quser() {
    }

    public Quser(BigDecimal id) {
        this.id = id;
    }

    public Quser(BigDecimal id, String username, String passwordHash, String realName) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.realName = realName;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
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
        if (!(object instanceof Quser)) {
            return false;
        }
        Quser other = (Quser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "studentsqueue.Quser[ id=" + id + " ]";
    }
    
}
