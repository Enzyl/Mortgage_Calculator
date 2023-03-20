package Mortgage_Calculator.service;

import Mortgage_Calculator.model.InputData;
import Mortgage_Calculator.model.TimePont;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class TimePointServiceImpl implements TimePointService{

    private static final BigDecimal YEAR = BigDecimal.valueOf(12);
    @Override
    public TimePont calculate(BigDecimal rateNumber, InputData inputData) {
        LocalDate date = calculateDate(rateNumber, inputData);

        BigDecimal year = calcualteYear(rateNumber);
        BigDecimal month = calculateMonth(rateNumber);

        return new TimePont(date,year,month);
    }

    private static LocalDate calculateDate(BigDecimal rateNumber, InputData inputData) {
        return inputData.getRepaymentStartDate()
                .plus(rateNumber.subtract(BigDecimal.ONE).intValue(), ChronoUnit.MONTHS);
    }

    private BigDecimal calcualteYear(final BigDecimal rateNumber){
        return  rateNumber.divide(YEAR, RoundingMode.UP).max(BigDecimal.ONE);
    }
    private BigDecimal calculateMonth(final BigDecimal rateNumber){
        return BigDecimal.ZERO.equals(rateNumber.remainder(YEAR)) ? YEAR : rateNumber.remainder(YEAR);
    }

}
