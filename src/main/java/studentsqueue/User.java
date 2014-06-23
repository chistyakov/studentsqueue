package studentsqueue;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

@XmlTransient
@Entity
@Table(name = "QUSER")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="USER_ROLE")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT q FROM User q"),
    @NamedQuery(name = "User.findById", query = "SELECT q FROM User q WHERE q.id = :id"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT q FROM User q WHERE q.username = :username"),
    @NamedQuery(name = "User.findByPasswordHash", query = "SELECT q FROM User q WHERE q.passwordHash = :passwordHash"),
    @NamedQuery(name = "User.findByRealName", query = "SELECT q FROM User q WHERE q.realName = :realName")})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    // use allocationSize=1 because of this shit: http://stackoverflow.com/a/20267392
    @SequenceGenerator(name="USER_ID_GEN", sequenceName="USERS_SEQ", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_ID_GEN")
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @Size(min = 1, max = 20)
    @NotNull
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @Size(min = 1, max = 20)
    @NotNull
    @Column(name = "PASSWORD_HASH")
    @XmlTransient
    private String passwordHash;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "REAL_NAME")
    private String realName;

    public User() {
    }

    public User(BigDecimal id) {
        this.id = id;
    }

    public User(BigDecimal id, String username, String passwordHash, String realName) {
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

    @XmlTransient
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
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
