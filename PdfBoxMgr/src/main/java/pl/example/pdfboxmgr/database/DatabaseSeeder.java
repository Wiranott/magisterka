package pl.example.pdfboxmgr.database;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final DocumentDataRepository documentDataRepository;

    public DatabaseSeeder(DocumentDataRepository documentDataRepository) {
        this.documentDataRepository = documentDataRepository;
    }

    @Override
    public void run(String... args) {
        if (documentDataRepository.count() == 0) {
            for (int i = 1; i <= 20; i++) {
                DocumentDataEntity data = new DocumentDataEntity();
                data.setContent("Testowy wpis " + i);
                documentDataRepository.save(data);
            }
        }
    }
}