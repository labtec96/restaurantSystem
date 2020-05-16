package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.SecondaryTable;

/**
 * Created by ch on 2020-05-16
 */
@Setter
@Getter
@NoArgsConstructor
@Entity
public class Worker extends User{
    private int Salary;
}
