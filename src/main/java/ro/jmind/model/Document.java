package ro.jmind.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Document implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "document_generator")
    @SequenceGenerator(name = "document_generator", sequenceName = "document_seq", allocationSize = 50)
    private Long id;

    private String description;

    public Document() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return Objects.equals(id, document.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
