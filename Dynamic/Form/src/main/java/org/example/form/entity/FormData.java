package org.example.form.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "FormData", catalog = "Data", schema = "dbo")
public class FormData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @OneToOne
    @JoinColumn(name = "formTemplate_fk")
    private FormTemplate formTemplate;

    @OneToMany(mappedBy = "formData",
            cascade = CascadeType.ALL, orphanRemoval = true)
    List<FieldData> fieldsData;
}
