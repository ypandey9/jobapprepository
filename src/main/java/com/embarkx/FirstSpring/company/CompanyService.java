package com.embarkx.FirstSpring.company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    List<Company> getAllCompanies();

    Company getById(Long id);
    boolean updateCompany(Company company,Long id);

    void createCompany(Company company);

    boolean deleteCompany(Long id);
}
