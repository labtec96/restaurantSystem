package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by ch on 2020-05-16
 */
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "meal")
public class Meal extends BaseEntity{

    private String name;
    private String description;
    private String weight;
    private int price;
}
