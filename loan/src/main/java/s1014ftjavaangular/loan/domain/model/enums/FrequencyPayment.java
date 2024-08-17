package s1014ftjavaangular.loan.domain.model.enums;

public enum FrequencyPayment {
    DAILY(1), WEEKLY(7), BI_WEEKLY(14), MONTHLY(1);

    private Integer value;

    FrequencyPayment(Integer value){
        this.value = value;
    }
    public Integer getValue(){
        return this.value;
    }

    public static FrequencyPayment stringToEnum(String frequencyPayment){
        frequencyPayment = frequencyPayment.toUpperCase();
        return FrequencyPayment.valueOf(frequencyPayment);
    }
}
