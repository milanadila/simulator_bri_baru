package com.bri.simulator.baseresponse;


import lombok.Data;

@Data
public class BaseResponse<T> {
    private T Data;
}
