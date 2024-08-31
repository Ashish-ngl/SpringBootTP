package com.example.demo2.service;

import com.example.demo2.model.Employee;
import com.example.demo2.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo repo;

    public List<Employee> getAllEmployees(Integer pagenumber, Integer pagesize) {

        Pageable p= PageRequest.of(pagenumber,pagesize);
        Page<Employee> pageEmployee=this.repo.findAll(p);
        return pageEmployee.getContent();
    }

    public Employee getEmployee(int id) {
        return repo.findById(id).orElse(null);
    }


    public Employee addEmployee(Employee employee){
        return repo.save(employee);
    }

    public Employee updateEmployee(int id, Employee employee) throws IOException {

        return repo.save(employee);
    }

    public void deleteEmployee(int id) {
        repo.deleteById(id);
    }


    public List<Employee> searchEmployees(String keyword) {
        return repo.searchEmployees(keyword);
    }




    //    public Employee addEmployeeWithImage(Employee employee, MultipartFile imageFile) throws IOException {
//        employee.setImageName(imageFile.getOriginalFilename());
//        employee.setImageType(imageFile.getContentType());
//        employee.setImageData(imageFile.getBytes());
//
//        return repo.save(employee);
//    }

//    public Employee updateEmployee(int id, Employee employee, MultipartFile imageFile) throws IOException {
//
//        employee.setImageName(imageFile.getOriginalFilename());
//        employee.setImageType(imageFile.getContentType());
//        employee.setImageData(imageFile.getBytes());
//        return repo.save(employee);
//    }

}
