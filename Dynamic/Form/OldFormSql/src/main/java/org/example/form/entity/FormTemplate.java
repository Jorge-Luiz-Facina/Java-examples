package org.example.form.entity;

import lombok.Data;
import org.example.form.enums.SystemTypeEnum;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "FormTemplate", catalog = "Data", schema = "dbo")
public class FormTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String type;

    @Column
    private String name;

    @Column
    private Integer version;

    @Column
    private SystemTypeEnum system;

    @OneToMany(mappedBy = "formTemplate")
    List<FieldTemplate> fieldTemplates;
}
