package org.example.form.entity;

import lombok.Data;
import org.example.form.enums.FieldTypeEnum;
import javax.persistence.*;

@Entity
@Data
@Table(name = "FieldTemplate", catalog = "Data", schema = "dbo")
public class FieldTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private Integer position;

    @Column
    @Enumerated(EnumType.STRING)
    private FieldTypeEnum type;

    @Column
    private Boolean isRequired;

    @Column
    private String output;

    @ManyToOne
    @JoinColumn(name = "formTemplate_fk")
    private FormTemplate formTemplate;
}
