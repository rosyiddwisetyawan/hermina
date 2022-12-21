package com.api.hermina.controller;

import com.api.hermina.model.AuthenticationRequest;
import com.api.hermina.model.SubmitRequest;
import com.api.hermina.model.db.Data;
import com.api.hermina.repository.DataRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@RestController
public class SubmitController {
    private final DataRepository dataRepository;

    public SubmitController(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @PostMapping(value = "/submit")
    public ResponseEntity submit(@RequestBody @Valid final SubmitRequest submitRequest) {

        Map<String, Object> responseMap = new HashMap<String, Object>();
        HttpStatus respStatus = HttpStatus.BAD_REQUEST;
        int status = HttpStatus.OK.value();
        String message = "failed";

        Data data = new Data();
        data.setCity(submitRequest.getCity());
        data.setAddress(submitRequest.getAddress());
        data.setCountry(submitRequest.getCountry());
        data.setEmail(submitRequest.getEmail());
        data.setDateofbirth(LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(submitRequest.getDateofbirth())));
        data.setGender(submitRequest.getGender());
        data.setPin(submitRequest.getPin());
        data.setFirstname(submitRequest.getFirstname());
        data.setLastname(submitRequest.getLastname());
        data.setState(submitRequest.getState());
        data.setMobileno(submitRequest.getMobileno());
        dataRepository.save(data);
        if(dataRepository.findById(data.getId()) != null){
            respStatus = HttpStatus.OK;
            status = HttpStatus.OK.value();
            message = "success";
        }

        responseMap.put("message",message);
        responseMap.put("status",status);

        return new ResponseEntity(responseMap, respStatus);
    }
}
