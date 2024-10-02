package com.embarkx.FirstSpring.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<Company> getAllCompanies()
    {
        return companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    public Company getById(@PathVariable Long id)
    {

        return companyService.getById(id);
    }

    @PostMapping
    public ResponseEntity<String> addCompany(@RequestBody Company company)
    {
        companyService.createCompany(company);
        return new ResponseEntity<>("Company created successfully!!", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable Long id) {
        boolean deleted = companyService.deleteCompany(id);
        if (deleted){
            return new ResponseEntity<>("Company Deleted Successfully!!", HttpStatus.OK);
    } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id,@RequestBody Company company)
    {
        boolean updated=companyService.updateCompany(company,id);
        if(updated)
        {
            return new ResponseEntity<>("Company Updated Successfully!!",HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
