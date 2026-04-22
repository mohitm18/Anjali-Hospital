package com.spti.controller;

import com.spti.dto.patient.BillRequestDto;
import com.spti.dto.patient.BillResponseDto;
import com.spti.service.BillserviceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/billing")
public class BillController {

    @Autowired
    private BillserviceInterface billService;

    @PostMapping("/create")
    public String createBill(@RequestBody BillRequestDto dto){

        billService.createBill(dto);

        return "Bill Created";
    }
    @GetMapping("/pending")
    public List<BillResponseDto> pendingBills(){
        return billService.getPendingBills();
    }

    @PostMapping("/pay/{id}/{amount}")
    public String payBill(@PathVariable Long id,
                          @PathVariable Integer amount){

        billService.payBill(id, amount);

        return "Payment Successful";
    }

}

