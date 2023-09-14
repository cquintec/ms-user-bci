
package cl.bci.ms.user.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Phone.
 *
 * @author Claudio Quinteros.
 * @version 1.0.0, 12-09-2023
 */
@Data
@Entity
@Table(name="phone")
public class Phone {

//    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long phoneId;

    @Column(name="number")
    private String number;

    @Column(name="city_code")
    private String cityCode;

    @Column(name="country_code")
    private String countryCode;

//    @ManyToOne(fetch = FetchType.EAGER, optional = false)
//    @JoinColumn(name="user_id",referencedColumnName = "user_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;


    @Override
    public String toString() {
        return "Phone [id=" + phoneId + ", number=" + number + ", cityCode=" + cityCode + ", contryCode="
                + countryCode + "]";
    }

    public Long getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(Long phoneId) {
        this.phoneId = phoneId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getContryCode() {
        return countryCode;
    }

    public void setContryCode(String contryCode) {
        this.countryCode = contryCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}