package pl.example.pdfitextmgr.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentDataRepository extends JpaRepository<DocumentDataEntity, Long> {

}