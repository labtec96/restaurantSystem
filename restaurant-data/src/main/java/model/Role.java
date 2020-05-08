package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Created by ch on 2020-05-06
 */
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role extends BaseEntity{

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role(String name) {
        this.name = name;
    }
}
