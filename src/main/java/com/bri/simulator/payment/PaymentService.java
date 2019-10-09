package com.bri.simulator.payment;

import com.bri.simulator.baseresponse.ResponsePayment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.Scanner;

@Slf4j
@Service
public class PaymentService {

    @Autowired
    Environment env;


    Scanner scan = new Scanner(System.in);

    public ResponseEntity<ResponsePayment> doPayment(@RequestBody PaymentRequest paymentRequest) {
        PaymentResponse paymentResponse = new PaymentResponse();
        ResponseEntity<ResponsePayment> res;

        String noReferral = paymentRequest.getNoReferral();

        //amount
        String amount = paymentRequest.getAmount();
        String feeType = paymentRequest.getFeeType();
        Double feeAmount = 6500.00;
//        BigDecimal totalAmount = new BigDecimal(amount).add(BigDecimal.valueOf(6500));
        Double penerima = 0.00;
        Double pengirim = 0.00;


        if (feeType.equals("OUR")) {
            penerima = Double.valueOf(amount);
            pengirim = Double.valueOf(amount) + feeAmount;
        } else if (feeType.equals("BEN")) {
            penerima = Double.valueOf(amount) - feeAmount;
            pengirim = Double.valueOf(amount);
        } else if (feeType.matches("^[SHA]+[|]+[0-9]{4}$")) {
            String feeSHA = feeType.substring(feeType.length()-4);
            penerima = Double.valueOf(amount) - (feeAmount - Double.valueOf(feeSHA));
            pengirim = Double.valueOf(amount) + Double.valueOf(feeSHA);
        }


        if (paymentRequest.getNoReferral() == null || paymentRequest.getNoReferral().isEmpty() || paymentRequest.getNoReferral().length() < 4) {

        }

        String errorCode = noReferral.substring(noReferral.length() - 4);
        String errorMsg = env.getProperty("err.code." + errorCode);
        String successMsg = env.getProperty("err.code.0200");

        //gagal sesuai skenario
        if (errorMsg != null) {
            res = setResponse(HttpStatus.BAD_REQUEST, paymentResponse, noReferral , feeType, pengirim, penerima);
        } else {
            res = setResponse(HttpStatus.OK, paymentResponse, noReferral, feeType, pengirim, penerima);
        }

        return res;
    }

    private ResponseEntity<ResponsePayment> setResponse(HttpStatus httpStatus, PaymentResponse paymentResponse, String NoReferral, String feeType, Double pengirim, Double penerima) {
        ResponsePayment response = new ResponsePayment();
        String errCode = NoReferral.substring(NoReferral.length()-4);
        String errMsg = env.getProperty("err.code." + errCode);
        String successMsg = env.getProperty("err.code.0200");

        if (errMsg != null) {
            response.setResponseCode(errCode);
            response.setResponseDescription("Payment failed");
            response.setErrorDescription(errMsg);
            response.setJournalSeq("");
        } else {
            response.setResponseCode(errCode);
            response.setResponseDescription(successMsg);
            response.setErrorDescription("");
            response.setJournalSeq("3289331");
            log.info("fee type : " + feeType);
            log.info("pengirim : " + pengirim);
            log.info("penerima : " + penerima);
        }

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }
}
