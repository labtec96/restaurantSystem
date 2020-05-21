package services.springdatajpa;

import lombok.extern.slf4j.Slf4j;
import model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.ReportService;
import services.ReservationService;
import util.Utils;

import java.time.DayOfWeek;
import java.time.Month;

/**
 * Created by ch on 2020-05-21
 */
@Slf4j
@Service
public class ReportServiceImpl implements ReportService{

    @Autowired
    ReservationService reservationService;

    @Override
    public Report reservationPerDay() {
        Report report = new Report();

        report.setReservationsForMonday(countReservationForDay(DayOfWeek.MONDAY));
        report.setReservationsForTuesday(countReservationForDay(DayOfWeek.TUESDAY));
        report.setReservationsForWednesday(countReservationForDay(DayOfWeek.WEDNESDAY));
        report.setReservationsForThursday(countReservationForDay(DayOfWeek.THURSDAY));
        report.setReservationsForFriday(countReservationForDay(DayOfWeek.FRIDAY));
        report.setReservationsForSaturday(countReservationForDay(DayOfWeek.SATURDAY));
        report.setReservationsForSunday(countReservationForDay(DayOfWeek.SUNDAY));
        return report;
    }

    @Override
    public Report reservationPerMonth() {
        Report report = new Report();
        report.setReservationsForJanuary(countReservationForMonth(Month.JANUARY));
        report.setReservationsForFebruary(countReservationForMonth(Month.FEBRUARY));
        report.setReservationsForMarch(countReservationForMonth(Month.MARCH));
        report.setReservationsForApril(countReservationForMonth(Month.APRIL));
        report.setReservationsForMay(countReservationForMonth(Month.MAY));
        report.setReservationsForJune(countReservationForMonth(Month.JUNE));
        report.setReservationsForJuly(countReservationForMonth(Month.JULY));
        report.setReservationsForAugust(countReservationForMonth(Month.AUGUST));
        report.setReservationsForSeptember(countReservationForMonth(Month.SEPTEMBER));
        report.setReservationsForOctober(countReservationForMonth(Month.OCTOBER));
        report.setReservationsForNovember(countReservationForMonth(Month.NOVEMBER));
        report.setReservationsForDecember(countReservationForMonth(Month.DECEMBER));
        return report;
    }

    @Override
    public Report reservationNumberOfPeople() {
        return null;
    }

    @Override
    public Report expenditureOnSalaries() {
        return null;
    }

    @Override
    public Report employees() {
        return null;
    }

    private Long countReservationForDay(DayOfWeek dayOfWeek){
        return reservationService.findAll().stream()
                .filter(reservation -> Utils.getDayOfWeek(reservation.getDate()).equals(dayOfWeek))
                .count();
    }

    private Long countReservationForMonth(Month month){
        return reservationService.findAll().stream()
                .filter(reservation -> Utils.getMonthOfDate(reservation.getDate()).equals(month))
                .count();
    }
}
