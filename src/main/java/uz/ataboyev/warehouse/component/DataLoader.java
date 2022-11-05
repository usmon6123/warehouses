package uz.ataboyev.warehouse.component;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    @Value("${dataLoaderMode}")
    private String dataLoaderMode;



    @Override
    public void run(String... args) throws Exception {
        initMethods();
    }

    private void initMethods() {

        saveDefaultUser();



    }

    private void saveDefaultUser() {
        // TODO: 05/11/22
    }
}
