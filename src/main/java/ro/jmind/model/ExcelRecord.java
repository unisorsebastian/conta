package ro.jmind.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Setter
@Getter
public class ExcelRecord {
    private Long id;
    private String serial;
}
