package services.springdatajpa;

import lombok.extern.slf4j.Slf4j;
import model.Cook;
import model.Manager;
import model.Report;
import model.Waiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.*;
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

    @Autowired
    ManagerService managerService;

    @Autowired
    WaiterService waiterService;

    @Autowired
    CookService cookService;


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
        Report report = new Report();
        report.setOnePersonReservation(countReservationForPersons(1));
        report.setTwoPersonReservation(countReservationForPersons(2));
        report.setThreePersonReservation(countReservationForPersons(3));
        report.setFourPersonReservation(countReservationForPersons(4));
        report.setFivePersonReservation(countReservationForPersons(5));
        report.setSixPersonReservation(countReservationForPersons(6));
        report.setSevenPersonReservation(countReservationForPersons(7));
        report.setEightPersonReservation(countReservationForPersons(8));
        report.setNinePersonReservation(countReservationForPersons(9));
        report.setTenPersonReservation(countReservationForPersons(10));
        return report;
    }

    @Override
    public Report expenditureOnSalaries() {
        Report report = new Report();
        report.setCookExpenditureOnSalaries(sumExpenditureCook());
        report.setManagerExpenditureOnSalaries(sumExpenditureManager());
        report.setWaiterExpenditureOnSalaries(sumExpenditureWaiter());
        return report;
    }

    @Override
    public Report employees() {
        Report report = new Report();
        report.setCooksHired(countCooks());
        report.setManagersHired(countManagers());
        report.setWaiterEsHired(countWaiters());
        return report;
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

    private Long countReservationForPersons(int persons){
        return reservationService.findAll().stream()
                .filter(reservation ->reservation.getPersons() ==persons)
                .count();
    }

    private int sumExpenditureCook(){
        return cookService.findAll().stream()
                .mapToInt(Cook::getSalary)
                .sum();
    }

    private int sumExpenditureManager(){
        return managerService.findAll().stream()
                .mapToInt(Manager::getSalary)
                .sum();
    }

    private int sumExpenditureWaiter(){
        return waiterService.findAll().stream()
                .mapToInt(Waiter::getSalary)
                .sum();
    }

    private int countCooks(){
        return cookService.findAll().size();
    }

    private int countManagers(){
        return managerService.findAll().size();
    }

    private int countWaiters(){
        return waiterService.findAll().size();
    }
}
