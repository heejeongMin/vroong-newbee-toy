package com.vroong.newbee.application.Controller.admin;


import com.vroong.newbee.application.model.request.CustomerReq;
import com.vroong.newbee.application.model.response.CustomerRes;
import com.vroong.newbee.application.service.admin.AdminUserManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AdminUserManagementController {

  private final AdminUserManagementService adminUserManagementService;


  @PostMapping("/admin/{partner}/v1/create")
  public ResponseEntity<CustomerRes> createCustomer(@PathVariable String partner, @RequestBody CustomerReq customerReq){
    ResponseEntity responseEntity;

    switch(partner.toUpperCase()) {
      case "CUSTOMER" : responseEntity = new ResponseEntity<CustomerRes>((CustomerRes) adminUserManagementService.createCustomer(customerReq), HttpStatus.OK);
      default : responseEntity = new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    return responseEntity;
  }
}
