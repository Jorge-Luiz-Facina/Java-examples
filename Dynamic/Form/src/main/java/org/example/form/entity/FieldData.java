package org.example.form.entity;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name = "FieldData", catalog = "Data", schema = "dbo")
public class FieldData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @OneToOne
    @JoinColumn(name = "field_fk")
    private FieldTemplate fieldTemplate;

    private String value;

    @ManyToOne
    @JoinColumn(name = "formData_fk")
    private FormData formData;
}
