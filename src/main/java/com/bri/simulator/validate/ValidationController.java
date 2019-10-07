package com.bri.simulator.validate;

import com.bri.simulator.baseresponse.ValidateBaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "validate")
public class ValidationController {

    @Autowired
    ValidationService validationService;


    @PostMapping()
    public ResponseEntity<ValidateBaseResponse> doValidate(@RequestBody @Valid ValidationRequest validationRequest) {
        log.info("inquiry request: " + validationRequest.toString());
        return validationService.doValidate(validationRequest);
    }
}
