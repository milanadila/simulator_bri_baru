package com.bri.simulator.checkstatus;

import com.bri.simulator.baseresponse.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CheckStatusService {

    @Autowired
    Environment env;

    public ResponseEntity<Response> doCheckStatus(String noReferral) {
        CheckStatusResponse checkStatusResponse = new CheckStatusResponse();
        ResponseEntity<Response> res;

        String errCode = noReferral.substring(noReferral.length() - 4);
        String errMsg = env.getProperty("err.code." + errCode);

        if (errMsg != null) {
            res = setResponse(HttpStatus.BAD_REQUEST, checkStatusResponse, noReferral);
        } else {
            checkStatusResponse.setNoReferral(noReferral);
            checkStatusResponse.setJournalSeq("3289331");
            checkStatusResponse.setInternalTransferStatus("Payment Success");
            checkStatusResponse.setInternalTransferErrorMessage("");
            res = setResponse(HttpStatus.OK, checkStatusResponse, noReferral);
        }
        return res;

    }

    private ResponseEntity<Response> setResponse(HttpStatus httpStatus, CheckStatusResponse checkStatusResponse, String NoReferral) {
        Response response = new Response();
        String errCode = NoReferral.substring(NoReferral.length()-4);
        String errMsg = env.getProperty("err.code." + errCode);
        String successMsg = env.getProperty("err.code.0300");

        if (errMsg != null) {
            response.setResponseCode(errCode);
            response.setResponseDescription("Check status failed");
            response.setErrorDescription(errMsg);
            response.setData(checkStatusResponse);
        } else {
            response.setResponseCode("0300");
            response.setResponseDescription(successMsg);
            response.setErrorDescription("");
            response.setData(checkStatusResponse);
        }
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }
}
