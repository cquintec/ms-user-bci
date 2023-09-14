
package cl.bci.ms.user.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * User.
 *
 * @author Claudio Quinteros.
 * @version 1.0.0, 12-09-2023
 */
@Data
@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "user_id", updatable = false, nullable = false)
    @JdbcTypeCode(SqlTypes.LONGVARCHAR)
    private UUID userId;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="token",length = 1024)
    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created")
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="last_login")
    private Date lastLogin;

    @Column(name="is_active")
    private boolean isActive;

    @JsonIgnore
    @OneToMany(targetEntity = Phone.class, mappedBy="user",cascade = CascadeType.ALL)
    private List<Phone> phones;

    public void addPhones(Phone phone) {
        phones.add(phone);
        phone.setUser(this);
    }


}