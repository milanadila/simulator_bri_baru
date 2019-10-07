package com.bri.simulator.validate;

import com.bri.simulator.baseresponse.BaseResponse;
import com.bri.simulator.baseresponse.ValidateBaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Service
public class ValidationService {

    @Autowired
    private Environment env;

    public ResponseEntity<ValidateBaseResponse> doValidate(@RequestBody ValidationRequest validationRequest) {
        ValidationResponse validationResponse = new ValidationResponse();
        ResponseEntity<ValidateBaseResponse> res;

        String beneficiaryAcc = validationRequest.getBeneficiaryAccount();

        String errCode = beneficiaryAcc.substring(beneficiaryAcc.length() - 4);
        String errMsg = env.getProperty("err.code." + errCode);
        String successMsg = env.getProperty("err.code.0100");

        if (errMsg != null) {
            validationResponse.setResponseCode(errCode);
            validationResponse.setResponseDescription("Inquiry failed");
            validationResponse.setErrorDescription(errMsg);
            res = setResponse(HttpStatus.BAD_REQUEST, validationResponse);
        } else {
            validationResponse.setResponseCode("0100");
            validationResponse.setResponseDescription(successMsg);
            validationResponse.setErrorDescription("");
            validationResponse.setSourceAccount(validationRequest.getSourceAccount());
            validationResponse.setSourceAccountName("BRIAPI SANDBOX");
            validationResponse.setSourceAccountStatus("Rekening Aktif");
            validationResponse.setSourceAccountBalace("258544125122.98");
            validationResponse.setRegistrationStatus("Rekening terdaftar an. bri");
            validationResponse.setBeneficiaryAccount(validationRequest.getBeneficiaryAccount());
            validationResponse.setBeneficiaryAccountName("BRIAPI SANDBOX 2");
            validationResponse.setBeneficiaryAccountStatus("Rekening Aktif");
            res = setResponse(HttpStatus.OK, validationResponse);
        }
        return res;
    }

    private static ResponseEntity<ValidateBaseResponse> setResponse(HttpStatus httpStatus, ValidationResponse validationResponse) {
        BaseResponse<ValidationResponse> response = new BaseResponse<>();
        ValidateBaseResponse vbs = new ValidateBaseResponse();
        response.setData(validationResponse);
        vbs.setGetValidateResponse(response);
        return ResponseEntity.status(httpStatus).body(vbs);
    }
}
