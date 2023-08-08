package pl.example.pdfboxmgr.DocumentData;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "document_data")
public class DocumentDataEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String content;

    public DocumentDataEntity(String content) {
        this.content = content;
    }

    public DocumentDataEntity() {

    }
}
