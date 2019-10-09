package com.bri.simulator.validate;

import com.bri.simulator.baseresponse.Response;
import com.bri.simulator.baseresponse.ValidateBaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping(value = "v2/transfer/internal/accounts")
public class ValidationController {

    @Autowired
    ValidationService validationService;


    @GetMapping
    public ResponseEntity<Response> doValidate(@RequestParam("sourceAccount") String sourceAccount, @RequestParam ("beneficiaryAccount") String beneficiaryAccount) {
        return validationService.doValidate(sourceAccount, beneficiaryAccount);
    }
}
