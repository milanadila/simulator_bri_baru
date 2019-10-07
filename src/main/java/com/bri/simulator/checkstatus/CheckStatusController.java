package com.bri.simulator.checkstatus;

import com.bri.simulator.baseresponse.CheckStatusBaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "checkstatus")
public class CheckStatusController {


    @Autowired
    CheckStatusService checkStatusService;

    @PostMapping
    public ResponseEntity<CheckStatusBaseResponse> doCheckStatus(@RequestBody @Valid CheckStatusRequest checkStatusRequest) {
        return checkStatusService.doCheckStatus(checkStatusRequest);
    }
}
