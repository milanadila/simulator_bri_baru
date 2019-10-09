package com.bri.simulator.validate;

import lombok.Data;

@Data
public class ValidationResponse {

    private String sourceAccount;
    private String sourceAccountName;
    private String sourceAccountStatus;
    private String sourceAccountBalace;
    private String registrationStatus;
    private String beneficiaryAccount;
    private String beneficiaryAccountName;
    private String beneficiaryAccountStatus;
}
