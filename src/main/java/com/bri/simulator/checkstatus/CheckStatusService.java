package com.bri.simulator.checkstatus;

import com.bri.simulator.baseresponse.BaseResponse;
import com.bri.simulator.baseresponse.CheckStatusBaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class CheckStatusService {

    @Autowired
    Environment env;

    public ResponseEntity<CheckStatusBaseResponse> doCheckStatus(@RequestBody CheckStatusRequest checkStatusRequest) {
        CheckStatusResponse checkStatusResponse = new CheckStatusResponse();
        ResponseEntity<CheckStatusBaseResponse> res;

        String noReferral = checkStatusRequest.getNoReferral();
        String errCode = noReferral.substring(noReferral.length() - 4);
        String errMsg = env.getProperty("err.code." + errCode);
        String successMsg = env.getProperty("err.code.0300");

        if (errMsg != null) {
            checkStatusResponse.setResponseCode(errCode);
            checkStatusResponse.setResponseDescription("Check status failed");
            checkStatusResponse.setErrorDescription(errMsg);
            res = setResponse(HttpStatus.BAD_REQUEST, checkStatusResponse);
        } else {
            checkStatusResponse.setResponseCode("0300");
            checkStatusResponse.setResponseDescription(successMsg);
            checkStatusResponse.setErrorDescription("");
            checkStatusResponse.setNoReferral(checkStatusRequest.getNoReferral());
            checkStatusResponse.setJournalSeq("3289331");
            checkStatusResponse.setInternalTransferStatus("Payment Success");
            checkStatusResponse.setInternalTransferErrorMessage("");
            res = setResponse(HttpStatus.OK, checkStatusResponse);
        }
        return res;

    }

    private static ResponseEntity<CheckStatusBaseResponse> setResponse(HttpStatus httpStatus, CheckStatusResponse checkStatusResponse) {
        BaseResponse<CheckStatusResponse> response = new BaseResponse<>();
        CheckStatusBaseResponse cbs = new CheckStatusBaseResponse();
        response.setData(checkStatusResponse);
        cbs.setDoCheckStatusResponse(response);
        return ResponseEntity.status(httpStatus).body(cbs);
    }
}
