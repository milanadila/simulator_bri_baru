package com.bri.simulator.checkstatus;


import lombok.Data;

@Data
public class CheckStatusResponse {

    private String responseCode;
    private String responseDescription;
    private String errorDescription;
    private String noReferral;
    private String journalSeq;
    private String internalTransferStatus;
    private String internalTransferErrorMessage;
}

