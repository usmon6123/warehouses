package uz.ataboyev.warehouse.component;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.ataboyev.warehouse.entity.Category;
import uz.ataboyev.warehouse.entity.Client;
import uz.ataboyev.warehouse.entity.Company;
import uz.ataboyev.warehouse.entity.Warehouse;
import uz.ataboyev.warehouse.enums.Type;
import uz.ataboyev.warehouse.repository.CategoryRepository;
import uz.ataboyev.warehouse.repository.ClientRepository;
import uz.ataboyev.warehouse.repository.CompanyRepository;
import uz.ataboyev.warehouse.repository.WarehouseRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final CompanyRepository companyRepository;
    private final WarehouseRepository warehouseRepository;
    private final CategoryRepository categoryRepository;
    private final ClientRepository clientRepository;
    public static final String BOSS_NAME = "Begzod boss";


    @Value("${dataLoaderMode}")
    private String dataLoaderMode;


    @Override
    public void run(String... args) throws Exception {
        initMethods();
    }

    private void initMethods() {

        if (dataLoaderMode.equals("always")) {
            Company company = new Company("NAZ");
            companyRepository.save(company);
            Warehouse warehouse1 = new Warehouse("naz Sklad 1", company.getId());
            Warehouse warehouse2 = new Warehouse("Sklad 2", company.getId());
            warehouseRepository.saveAll(List.of(warehouse1, warehouse2));
//            ArrayList<Category> list = new ArrayList<>();
//            list.add(new Category("Moyka 1", warehouse1.getId()));
//            list.add(new Category("Unitaz 1", warehouse1.getId()));
//            list.add(new Category("Mebel 1", warehouse1.getId()));
//            list.add(new Category("Moyka 2", warehouse2.getId()));
//            list.add(new Category("Unitaz 2", warehouse2.getId()));
//            list.add(new Category("Mebel 2", warehouse2.getId()));
//            categoryRepository.saveAll(list);

            Client client = new Client(Type.BOSS, BOSS_NAME, "+998 (**) *** ** **");
            clientRepository.save(client);


        }


    }

    private void saveDefaultUser() {
        // TODO: 05/11/22
    }
}
