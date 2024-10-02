package com.embarkx.FirstSpring.company.impl;

import com.embarkx.FirstSpring.company.Company;
import com.embarkx.FirstSpring.company.CompanyRepository;
import com.embarkx.FirstSpring.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public boolean updateCompany(Company company1, Long id) {
        Optional<Company> company=companyRepository.findById(id);
        if(company.isPresent()) {
            Company comp=company.get();
            comp.setName(company1.getName());
            comp.setDescription(company1.getDescription());
            comp.setJobs(company1.getJobs());
            companyRepository.save(comp);
            return true;
        }
        return false;
        }



    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompany(Long id) {
        try {
            companyRepository.deleteById(id);
            return true;
        }catch(Exception e)
        {
            return false;
        }

    }
}
