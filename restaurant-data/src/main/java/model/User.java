package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by ch on 2020-05-05
 */
@Getter
@Setter
@Entity
public class User extends BaseEntity{

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String username;

    private String password;

    private String email;

    @Transient
    private String passwordConfirm;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @ManyToMany
    private Collection<Role> roles;

    @Column(name = "enabled")
    private boolean enabled;

    public User() {
        super();
        this.enabled=false;
    }

    public User(Long id, String firstName, String lastName) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
