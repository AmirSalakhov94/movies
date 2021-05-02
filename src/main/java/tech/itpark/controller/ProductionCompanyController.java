package tech.itpark.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.itpark.dto.CompanyDto;
import tech.itpark.service.ProductionCompanyService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product/company")
public class ProductionCompanyController {

    private final ProductionCompanyService productionCompanyService;

    @GetMapping("/all")
    public List<CompanyDto> companies() {
        return productionCompanyService.getCompanies();
    }

    @GetMapping("/{id}")
    public CompanyDto company(@PathVariable("id") long id) {
        return productionCompanyService.getCompany(id);
    }
}
