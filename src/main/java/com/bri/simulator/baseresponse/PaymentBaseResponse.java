package com.bri.simulator.baseresponse;

import com.bri.simulator.payment.PaymentResponse;
import lombok.Data;

@Data
public class PaymentBaseResponse {
    BaseResponse<PaymentResponse> doPaymentResponse;
}
