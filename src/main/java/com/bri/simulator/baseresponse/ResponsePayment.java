package com.bri.simulator.baseresponse;

import lombok.Data;

@Data
public class ResponsePayment {
    private String responseCode;
    private String responseDescription;
    private String errorDescription;
    private String JournalSeq;
}
