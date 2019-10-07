package com.bri.simulator.validate;

import lombok.Data;

@Data
public class ValidationRequest {

    private String sourceAccount;
    private String beneficiaryAccount;
}
