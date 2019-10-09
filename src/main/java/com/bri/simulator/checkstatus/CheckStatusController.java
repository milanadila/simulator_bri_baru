package com.bri.simulator.checkstatus;

import com.bri.simulator.baseresponse.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "v2/transfer/internal")
public class CheckStatusController {


    @Autowired
    CheckStatusService checkStatusService;

    @GetMapping
    public ResponseEntity<Response> doCheckStatus(@RequestParam String noReferral) {
        return checkStatusService.doCheckStatus(noReferral);
    }
}
