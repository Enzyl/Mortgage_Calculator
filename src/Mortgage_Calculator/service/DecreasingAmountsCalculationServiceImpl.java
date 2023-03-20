package Mortgage_Calculator.service;

import Mortgage_Calculator.model.InputData;
import Mortgage_Calculator.model.Overpayment;
import Mortgage_Calculator.model.Rate;
import Mortgage_Calculator.model.RateAmounts;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static Mortgage_Calculator.utils.CalculationUtils.calculateInterestAmount;

public class DecreasingAmountsCalculationServiceImpl implements DecreasingAmountsCalculationService {
    @Override
    public RateAmounts calculate(InputData inputData, Overpayment overpayment) {
        BigDecimal interestPercent = inputData.getInterestPercent();

        BigDecimal residualAmount = inputData.getAmount();
        BigDecimal referenceAmount = inputData.getAmount();
        BigDecimal referenceDuration = inputData.getMonthsDuration();

        BigDecimal interestAmount = calculateInterestAmount(residualAmount, interestPercent);
        BigDecimal capitalAmount = calculateCapitalAmount(referenceAmount, referenceDuration, residualAmount);
        BigDecimal rateAmount = capitalAmount.add(interestAmount);
        return new RateAmounts(rateAmount, interestAmount, capitalAmount, overpayment);
    }

    @Override
    public RateAmounts calculate(InputData inputData, Overpayment overpayment, Rate previousRate) {
        BigDecimal interestPercent = inputData.getInterestPercent();

        BigDecimal residualAmount = previousRate.getMortgageResidual().getAmount();
        BigDecimal referenceAmount = previousRate.getMortgageReference().getAmount();
        BigDecimal referenceDuration = previousRate.getMortgageReference().getDuration();

        BigDecimal interestAmount = calculateInterestAmount(residualAmount, interestPercent);
        BigDecimal capitalAmount = calculateCapitalAmount(referenceAmount, referenceDuration, residualAmount);
        BigDecimal rateAmount = capitalAmount.add(interestAmount);
        return new RateAmounts(rateAmount, interestAmount, capitalAmount, overpayment);
    }

    private BigDecimal calculateCapitalAmount(
            BigDecimal amount,
            BigDecimal monthsDuration,
            BigDecimal residualAmount
    ) {
        BigDecimal capitalAmount = amount.divide(monthsDuration,10,RoundingMode.HALF_UP);
        if (capitalAmount.compareTo(residualAmount) >=0){
            return residualAmount;
        }
        return capitalAmount;
    }

}
