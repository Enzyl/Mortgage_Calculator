package Mortgage_Calculator;

import Mortgage_Calculator.model.InputData;
import Mortgage_Calculator.model.Overpayment;
import Mortgage_Calculator.model.RateType;
import Mortgage_Calculator.service.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        InputData inputData = new InputData()
                .withAmount(new BigDecimal("300000"))
                .withMonthsDuration(new BigDecimal("180"))
                .withRateType(RateType.CONSTANT)
                .withOverpaymentReduceWay(Overpayment.REDUCE_PERIOD);

        PrintingService printingService = new PrintingServiceImpl();
        RateCalculationService rateCalculationService = new RateCalculationServiceImpl(
                new TimePointServiceImpl(),
                new AmountsCalculationServiceImpl(
                        new ConstantAmountsCalculationServiceImpl(),
                        new DecreasingAmountsCalculationServiceImpl()
                ),
                new OverpaymentCalculationServiceImpl(),
                new ResidualCalculationServiceImpl(),
                new ReferenceCalculationServiceImpl()
        );

        MortgageCalculationService mortgageCalculationService = new MortgageCalculationServiceImpl(
                printingService,
                rateCalculationService,
                SummaryServiceFactory.create()
        );

        mortgageCalculationService.calculate(inputData);
    }
}