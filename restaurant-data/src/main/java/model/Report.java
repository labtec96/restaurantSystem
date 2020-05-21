package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * Created by ch on 2020-05-21
 */
@Setter
@Getter
@NoArgsConstructor
public class Report {

    private long reservationsForMonday;
    private long reservationsForTuesday;
    private long reservationsForWednesday;
    private long reservationsForThursday;
    private long reservationsForFriday;
    private long reservationsForSaturday;
    private long reservationsForSunday;

    private long reservationsForJanuary;
    private long reservationsForFebruary;
    private long reservationsForMarch;
    private long reservationsForApril;
    private long reservationsForMay;
    private long reservationsForJune;
    private long reservationsForJuly;
    private long reservationsForAugust;
    private long reservationsForSeptember;
    private long reservationsForOctober;
    private long reservationsForNovember;
    private long reservationsForDecember;

}
