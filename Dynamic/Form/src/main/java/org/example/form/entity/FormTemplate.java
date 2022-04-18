package org.example.form.entity;

import lombok.Data;
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
    private String name;

    @Column
    private Integer version;

    @Column
    private String system;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "formTemplate",
            cascade = CascadeType.ALL, orphanRemoval = true)
    List<FieldTemplate> fieldTemplates;
}
