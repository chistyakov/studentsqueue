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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author teamdevelopment
 */
@Entity
@Table(name = "TEACHER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Teacher.findAll", query = "SELECT t FROM Teacher t"),
    @NamedQuery(name = "Teacher.findByUserId", query = "SELECT t FROM Teacher t WHERE t.userId = :userId")})
public class Teacher implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "USER_ID")
    private BigDecimal userId;
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private User user;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacherId")
    private Collection<Queue> queueCollection;

    public Teacher() {
    }

    public Teacher(BigDecimal userId) {
        this.userId = userId;
    }

    public BigDecimal getUserId() {
        return userId;
    }

    public void setUserId(BigDecimal userId) {
        this.userId = userId;
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
    public Collection<Queue> getQueueCollection() {
        return queueCollection;
    }

    public void setQueueCollection(Collection<Queue> queueCollection) {
        this.queueCollection = queueCollection;
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
        if (!(object instanceof Teacher)) {
            return false;
        }
        Teacher other = (Teacher) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "studentsqueue.Teacher[ userId=" + userId + " ]";
    }
    
}
