package Mortgage_Calculator.service;

import Mortgage_Calculator.exceptions.MortgageException;
import Mortgage_Calculator.model.InputData;
import Mortgage_Calculator.model.Overpayment;
import Mortgage_Calculator.model.Rate;
import Mortgage_Calculator.model.RateAmounts;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AmountsCalculationServiceImpl implements AmountsCalculationService {

    private final  ConstantAmountsCalculationService constantAmountsCalculationService;
    private final  DecreasingAmountsCalculationService decreasingAmountsCalculationService;

    public AmountsCalculationServiceImpl(ConstantAmountsCalculationService constantAmountsCalculationService,
                                         DecreasingAmountsCalculationService decreasingAmountsCalculationService) {
        this.constantAmountsCalculationService = constantAmountsCalculationService;
        this.decreasingAmountsCalculationService = decreasingAmountsCalculationService;
    }

    @Override
    public RateAmounts calculate(InputData inputData, Overpayment overpayment) {
        switch (inputData.getRateType()) {
            case CONSTANT:
                return constantAmountsCalculationService.calculate(inputData,overpayment);
            case DECREASING:
                return decreasingAmountsCalculationService.calculate(inputData,overpayment);
            default:
                throw new MortgageException();
        }
    }

    @Override
    public RateAmounts calculate(InputData inputData, Overpayment overpayment, Rate previousRate) {
        switch (inputData.getRateType()) {
            case CONSTANT:
                return constantAmountsCalculationService.calculate(inputData,overpayment, previousRate);
            case DECREASING:
                return decreasingAmountsCalculationService.calculate(inputData,overpayment, previousRate);
            default:
                throw new MortgageException();
        }
    }

}
