package com.example.demo2.controller;

import com.example.demo2.model.Employee;
import com.example.demo2.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService service;


    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees(
            @RequestParam(value = "pagenumber",defaultValue = "0",required = false) Integer pagenum,
            @RequestParam(value = "pagesize",defaultValue = "10",required = false) Integer pagesize
    ){

        return new ResponseEntity<>(service.getAllEmployees( pagenum, pagesize), HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable int id) {
        Employee employee = service.getEmployee(id);
        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/employee")
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
        try {
            Employee employee1 = service.addEmployee(employee);
            return new ResponseEntity<>(employee1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/Employee/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable int id, @RequestBody Employee employee) {

        Employee employee1 = null;
        try {
            employee1 = service.updateEmployee(id, employee);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
        }
        if (employee1 != null) {
            return new ResponseEntity<>("updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
        }


    }


    @DeleteMapping("/employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
        Employee employee = service.getEmployee(id);
        if (employee != null) {
            service.deleteEmployee(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/employees/search")
    public ResponseEntity<List<Employee>> searchEmployees(@RequestParam String keyword) {

        List<Employee> employees = service.searchEmployees(keyword);
        System.out.println("searching with " + keyword);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }





//IMAGE PART
//
//    @PostMapping("/employee")
//    public ResponseEntity<?> addEmployee(@RequestPart Employee employee, @RequestPart MultipartFile imageFile) {
//        try {
//            Employee employee1 = service.addEmployeeWithImage(employee, imageFile);
//            return new ResponseEntity<>(employee1, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//
//    @GetMapping("/employee/{employeeId}/image")
//    public ResponseEntity<byte[]> getImageByEmployeeId(@PathVariable int employeeId) {
//        Employee employee = service.getEmployee(employeeId);
//        byte[] imageFile = employee.getImageData();
//
//        return ResponseEntity.ok().contentType(MediaType.valueOf(employee.getImageType())).body(imageFile);
////    }
//      @PutMapping("/Employee/{id}")
//      public ResponseEntity<String> updateEmployee(@PathVariable int id, @RequestPart Employee employee, @RequestPart MultipartFile imageFile) {
//
//      Employee employee1 = null;
//    try {
//        employee1 = service.updateEmployee(id, employee, imageFile);
//    } catch (IOException e) {
//        return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
//    }
//    if (employee1 != null) {
//        return new ResponseEntity<>("updated", HttpStatus.OK);
//    } else {
//        return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
//    }

}