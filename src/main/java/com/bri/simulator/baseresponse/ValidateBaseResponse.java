package com.bri.simulator.baseresponse;

import com.bri.simulator.validate.ValidationResponse;
import lombok.Data;

@Data
public class ValidateBaseResponse {
    BaseResponse<ValidationResponse> getValidateResponse;
}

