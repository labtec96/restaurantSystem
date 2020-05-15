package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by ch on 2020-05-14
 */
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "restaurant_table")
public class RestaurantTable extends BaseEntity{
    private String number;
    private String floor;
    private int maxNumberOfPeople;
}
