package s1014ftjavaangular.loan.domain.model.enums;

public enum AmortizationType {
    SIMPLE_INTEREST,
    OUTSTANDING_INTEREST,
    FIXED_INSTALLMENTS,
    CREDIT_LINE;

    public static AmortizationType stringToEnum(String amortizationType){
        amortizationType = amortizationType.toUpperCase();
        return AmortizationType.valueOf(amortizationType);
    }
}
