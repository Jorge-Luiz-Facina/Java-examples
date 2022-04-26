package org.example.form.config.dbmigrations;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import org.example.form.entity.FormTemplate;
import org.example.form.enums.SystemTypeEnum;
import org.example.form.repository.FormTemplateRepository;

@ChangeLog(order = "001")
public class DatabaseCreateFormTemplateType1Teste {

    @ChangeSet(order = "001", id = "type1_teste", author = "jorge.luiz.facina")
    public void createType1Teste(FormTemplateRepository formTemplateRepository) {

        FormTemplate formTemplate = new FormTemplate();
        formTemplate.setVersion(1);
        formTemplate.setType("TYPE1");
        formTemplate.setSystem(SystemTypeEnum.APPLICATION1);
        formTemplate.setName("TESTE");
        formTemplateRepository.save(formTemplate);
    }
}