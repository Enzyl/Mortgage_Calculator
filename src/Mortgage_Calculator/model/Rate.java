package Mortgage_Calculator.model;

import java.math.BigDecimal;

public class Rate {
    private final BigDecimal rateNumber;
    private final TimePont timePont;
    private final RateAmounts rateAmounts;
    private final MortgageResidual mortgageResidual;
    private final MortgageReference mortgageReference;

    public Rate(BigDecimal rateNumber,
                TimePont timePont,
                RateAmounts rateAmounts,
                MortgageResidual mortgageResidual,
                MortgageReference mortgageReference
    ) {
        this.rateNumber = rateNumber;
        this.timePont = timePont;
        this.rateAmounts = rateAmounts;
        this.mortgageResidual = mortgageResidual;
        this.mortgageReference = mortgageReference;
    }

    public BigDecimal getRateNumber() {
        return rateNumber;
    }

    public TimePont getTimePont() {
        return timePont;
    }

    public RateAmounts getRateAmounts() {
        return rateAmounts;
    }

    public MortgageResidual getMortgageResidual() {
        return mortgageResidual;
    }

    public MortgageReference getMortgageReference() {
        return mortgageReference;
    }
}
