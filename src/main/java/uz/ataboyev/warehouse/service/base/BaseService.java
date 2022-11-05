package uz.ataboyev.warehouse.service.base;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.ataboyev.warehouse.entity.Company;
import uz.ataboyev.warehouse.entity.Warehouse;
import uz.ataboyev.warehouse.exception.RestException;
import uz.ataboyev.warehouse.payload.WareHouseReqDto;
import uz.ataboyev.warehouse.repository.CategoryRepository;
import uz.ataboyev.warehouse.repository.CompanyRepository;
import uz.ataboyev.warehouse.repository.WarehouseRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BaseService {

    private final WarehouseRepository warehouseRepository;
    private final CategoryRepository categoryRepository;
    private final CompanyRepository companyRepository;


    public Warehouse getWarehouseByIdElseThrow(Long whId) {
        return warehouseRepository.findById(whId).orElseThrow(() -> RestException.notFound("Warehouse not found"));
    }

    public boolean existsWarehouse(Long wHId){
        return warehouseRepository.existsById(wHId);
    }
    public boolean existsWarehouseByCompId(Long compId){
        return warehouseRepository.existsByCompanyId(compId);
    }

    public boolean existsCompany(Long compId){
        return companyRepository.existsById(compId);
    }

    public Company getCompanyById(Long id){
        return companyRepository.findById(id).orElseThrow(() -> RestException.notFound("Company not found"));
    }

}
