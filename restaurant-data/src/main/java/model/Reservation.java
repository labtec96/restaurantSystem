package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by ch on 2020-05-16
 */
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "reservation")
public class Reservation extends BaseEntity{

    @Column(name = "date")
    private LocalDate date;

    private LocalTime startHour;

    private LocalTime endHour;

    @Column(name = "persons")
    private int persons;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne
    @JoinColumn(name = "restaurantTable_id")
    private RestaurantTable restaurantTable;

}
