package uz.ataboyev.warehouse.service.base;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.ataboyev.warehouse.entity.Client;
import uz.ataboyev.warehouse.entity.Company;
import uz.ataboyev.warehouse.entity.Product;
import uz.ataboyev.warehouse.entity.Warehouse;
import uz.ataboyev.warehouse.exception.RestException;
import uz.ataboyev.warehouse.payload.WareHouseReqDto;
import uz.ataboyev.warehouse.repository.*;
import uz.ataboyev.warehouse.service.ClientService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BaseService {

    private final WarehouseRepository warehouseRepository;
    private final CategoryRepository categoryRepository;
    private final CompanyRepository companyRepository;
    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;


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
     public boolean checkingClientByPhoneNumber(String phoneNumber){
        return clientRepository.existsByPhoneNumber(phoneNumber);
     }
    public boolean checkingClientById(Long id){
        return clientRepository.existsById(id);
    }

    public Client getClientById(Long clientId) {
        return clientRepository.findById(clientId).orElseThrow(() -> RestException.restThrow("Client not found"));
    }

    public boolean existsClientById(Long clientId) {
        return clientRepository.existsById(clientId);
    }

    public void checkOrdersOfClient(Long clientId){
        if (orderRepository.existsByClientId(clientId)) {
            throw RestException.restThrow("Bu mijozni o'chira olmaysiz, uning oldi berdilari bo'lgan ekan siz bn", HttpStatus.CONFLICT);
        }
    }

    public boolean checkCategoryById(Long categoryId){
        return categoryRepository.existsById(categoryId);
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> RestException.restThrow("Product mavjudmas"));

    }
}
