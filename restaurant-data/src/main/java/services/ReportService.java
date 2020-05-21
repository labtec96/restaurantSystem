package services;

import model.Report;

/**
 * Created by ch on 2020-05-21
 */
public interface ReportService {

    Report reservationPerDay();

    Report reservationPerMonth();

    Report reservationNumberOfPeople();

    Report expenditureOnSalaries();

    Report employees();
}
