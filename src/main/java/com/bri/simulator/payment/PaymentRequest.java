package com.bri.simulator.payment;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class PaymentRequest {

    private String noReferral;
    private String sourceAccount;
    private String beneficiaryAccount;
    private String amount;
    private String feeType;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private String transactionDateTime;
    private String remark;
}
