package com.rain.learn.sms.ebo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class UserEbo implements Serializable {

    private static final long serialVersionUID = -6182342701736883625L;
    public static final String NAME_PATH = "name";

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public int getId() {
        return id;
    }

    @SuppressWarnings("unused")
    private void setId(int id) {
        this.id = id;
    }

    @NotEmpty
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotEmpty
    @Column(name = "password")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "regist_time")
    private Date registTime = new Date();

    public Date getRegistTime() {
        return registTime;
    }

    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }

    @ManyToOne(targetEntity = GroupEbo.class)
    // @JoinTable(name = "group_members", joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") },
    // inverseJoinColumns = { @JoinColumn(name = "group_id", referencedColumnName = "id") })
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private GroupEbo group;

    public GroupEbo getGroup() {
        return group;
    }

    public void setGroup(GroupEbo group) {
        this.group = group;
    }

    @OneToMany(targetEntity = TokenEbo.class, fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<TokenEbo> tokens;

    public Set<TokenEbo> getTokens() {
        return tokens;
    }

    public void setTokens(Set<TokenEbo> tokens) {
        this.tokens = tokens;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((registTime == null) ? 0 : registTime.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserEbo other = (UserEbo) obj;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (registTime == null) {
            if (other.registTime != null)
                return false;
        } else if (!registTime.equals(other.registTime))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "UserEbo [id=" + id + ", name=" + name + ", registTime=" + registTime + "]";
    }

}
