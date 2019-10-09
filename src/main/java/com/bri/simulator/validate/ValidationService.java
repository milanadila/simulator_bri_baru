package com.bri.simulator.validate;

import com.bri.simulator.baseresponse.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ValidationService {

    @Autowired
    Environment env;

    public ResponseEntity<Response> doValidate(String sourceAccount, String beneficiaryAccount) {
        ValidationResponse validationResponse = new ValidationResponse();
        ResponseEntity<Response> res;

        String errCode = beneficiaryAccount.substring(beneficiaryAccount.length() - 4);
        String errMsg = env.getProperty("err.code." + errCode);
        String successsMsg = env.getProperty("err.code.0100");

        if (errMsg != null) {
            res = setResponse(HttpStatus.BAD_REQUEST, validationResponse, beneficiaryAccount);
        } else {
            validationResponse.setSourceAccount(sourceAccount);
            validationResponse.setSourceAccountName("BRIAPI SANDBOX");
            validationResponse.setSourceAccountStatus("Rekening Aktif");
            validationResponse.setSourceAccountBalace("258544125122.98");
            validationResponse.setRegistrationStatus("Rekening terdaftar an. bri");
            validationResponse.setBeneficiaryAccount(beneficiaryAccount);
            validationResponse.setBeneficiaryAccountName("BRIAPI SANDBOX 2");
            validationResponse.setBeneficiaryAccountStatus("Rekening Aktif");
            res = setResponse(HttpStatus.OK, validationResponse, beneficiaryAccount);
        }
        return res;
}

    private ResponseEntity<Response> setResponse(HttpStatus httpStatus, ValidationResponse validationResponse, String beneficiaryAccount) {
        Response response = new Response();
        String errCode = beneficiaryAccount.substring(beneficiaryAccount.length() - 4);
        String errMsg = env.getProperty("err.code." + errCode);
        String successMsg = env.getProperty("err.code.0100");

        if (errMsg != null) {
            response.setResponseCode(errCode);
            response.setResponseDescription("Inquiry failed");
            response.setErrorDescription(errMsg);
            response.setData(validationResponse);
        } else {
            response.setResponseCode("0100");
            response.setResponseDescription(successMsg);
            response.setErrorDescription("");
            response.setData(validationResponse);
        }
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }
}
