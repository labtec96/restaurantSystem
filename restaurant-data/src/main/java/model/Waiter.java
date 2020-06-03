package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * Created by ch on 2020-05-19
 */
@Setter
@Getter
@Entity
public class Waiter extends User{

    private String percentageOfTips;

    private int salary;

    private String accountNumber;

    public Waiter(){
        super();
    }
}
