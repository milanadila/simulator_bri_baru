package com.bri.simulator.baseresponse;

import lombok.Data;

@Data
public class Response<T> {
    private String responseCode;
    private String responseDescription;
    private String errorDescription;
    private T Data;
}
