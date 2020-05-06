package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by ch on 2020-05-05
 */
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer extends User {

}
