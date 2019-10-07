package com.bri.simulator.payment;

import com.bri.simulator.baseresponse.BaseResponse;
import com.bri.simulator.baseresponse.PaymentBaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Service
public class PaymentService {

    @Autowired
    private Environment env;


    public ResponseEntity<PaymentBaseResponse> doPayment(@RequestBody PaymentRequest paymentRequest) {
        PaymentResponse paymentResponse = new PaymentResponse();
        ResponseEntity<PaymentBaseResponse> res;

        String noReferral = paymentRequest.getNoReferral();

        if (paymentRequest.getNoReferral() == null || paymentRequest.getNoReferral().isEmpty() || paymentRequest.getNoReferral().length() < 4) {

        }


        String errorCode = noReferral.substring(noReferral.length() - 4);
        String errorMsg = env.getProperty("err.code." + errorCode);
        String successMsg = env.getProperty("err.code.0200");

        //gagal sesuai skenario
        if (errorMsg != null) {

            paymentResponse.setResponseCode(errorCode);
            paymentResponse.setResponseDescription("Payment failed");
            paymentResponse.setErrorDescription(errorMsg);
            res = setResponse(HttpStatus.BAD_REQUEST, paymentResponse);


        } else {
            paymentResponse.setResponseCode("0200");
            paymentResponse.setResponseDescription(successMsg);
            paymentResponse.setErrorDescription("");
            res = setResponse(HttpStatus.OK, paymentResponse);
        }

        return res;
    }

    private static ResponseEntity<PaymentBaseResponse> setResponse(HttpStatus httpStatus, PaymentResponse paymentResponse) {
        BaseResponse<PaymentResponse> response = new BaseResponse<>();
        PaymentBaseResponse pbs = new PaymentBaseResponse();
        response.setData(paymentResponse);
        pbs.setDoPaymentResponse(response);
        return ResponseEntity.status(httpStatus).body(pbs);
    }
}
