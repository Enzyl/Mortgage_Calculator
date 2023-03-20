package Mortgage_Calculator.service;

import Mortgage_Calculator.model.Rate;

import java.math.BigDecimal;

public interface Function {
    BigDecimal calculate(Rate rate);
}
