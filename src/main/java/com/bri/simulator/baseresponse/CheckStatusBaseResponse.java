package com.bri.simulator.baseresponse;

import com.bri.simulator.checkstatus.CheckStatusResponse;
import lombok.Data;

@Data
public class CheckStatusBaseResponse {
    BaseResponse<CheckStatusResponse> doCheckStatusResponse;
}
