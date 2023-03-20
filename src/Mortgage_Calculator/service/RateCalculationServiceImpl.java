package Mortgage_Calculator.service;

import Mortgage_Calculator.model.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

public class RateCalculationServiceImpl implements RateCalculationService {

    private final TimePointService timePointService;
    private final AmountsCalculationService amountsCalculationService;
    private final OverpaymentCalculationService overpaymentCalculationService;
    private final ResidualCalculationService residualCalculationService;
    private final ReferenceCalculationService referenceCalculationService;

    public RateCalculationServiceImpl(TimePointService timePointService,
                                      AmountsCalculationService amountsCalculationService,
                                      OverpaymentCalculationService overpaymentCalculationService,
                                      ResidualCalculationService residualCalculationService,
                                      ReferenceCalculationService referenceCalculationService
    ) {
        this.timePointService = timePointService;
        this.amountsCalculationService = amountsCalculationService;
        this.overpaymentCalculationService = overpaymentCalculationService;
        this.residualCalculationService = residualCalculationService;
        this.referenceCalculationService = referenceCalculationService;
    }

    @Override
    public List<Rate> calculate(InputData inputData) {
        List<Rate> rates = new LinkedList<>();

        BigDecimal rateNumber = BigDecimal.ONE;

        Rate firstRate = calculate(rateNumber, inputData);

        rates.add(firstRate);

        Rate previousRate = firstRate;

        for (BigDecimal index = rateNumber.add(BigDecimal.ONE);
             index.compareTo(inputData.getMonthsDuration()) <= 0;
             index = index.add(BigDecimal.ONE)
        ){
            Rate nextRate = calculate(index, inputData, previousRate);
            rates.add(nextRate);
            previousRate = nextRate;

            if (mortgageFinished(nextRate)){
                break;
            }
        }


        return rates;
    }

    private static boolean mortgageFinished(Rate nextRate) {
        BigDecimal toCompare = nextRate.getMortgageResidual().getAmount().setScale(0, RoundingMode.HALF_UP);
        return BigDecimal.ZERO.equals(toCompare);
    }

    private Rate calculate(BigDecimal rateNumber, InputData inputData) {
        TimePont timePont = timePointService.calculate(rateNumber, inputData);
        Overpayment overpayment = overpaymentCalculationService.calculate(rateNumber,inputData);
        RateAmounts rateAmounts = amountsCalculationService.calculate(inputData,overpayment);
        MortgageResidual mortgageResidual = residualCalculationService.calculate(rateAmounts,inputData);
        MortgageReference mortgageReference = referenceCalculationService.calculate(inputData);
        return new Rate(rateNumber,timePont,rateAmounts,mortgageResidual,mortgageReference);
    }

    private Rate calculate(BigDecimal rateNumber, InputData inputData, Rate previousRate) {
        TimePont timePont = timePointService.calculate(rateNumber, inputData);
        Overpayment overpayment = overpaymentCalculationService.calculate(rateNumber,inputData);
        RateAmounts rateAmounts = amountsCalculationService.calculate(inputData, overpayment, previousRate);
        MortgageResidual mortgageResidual = residualCalculationService.calculate(rateAmounts, previousRate);
        MortgageReference mortgageReference = referenceCalculationService.calculate(inputData,rateAmounts,previousRate);

        return new Rate(rateNumber,timePont,rateAmounts,mortgageResidual,mortgageReference);
    }
}
