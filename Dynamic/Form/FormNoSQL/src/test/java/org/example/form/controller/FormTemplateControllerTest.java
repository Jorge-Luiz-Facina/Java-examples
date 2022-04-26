package org.example.form.controller;

import org.example.form.Application;
import org.example.form.dto.request.FieldTemplateRequest;
import org.example.form.dto.request.FormTemplateRequest;
import org.example.form.dto.response.FieldTemplateResponse;
import org.example.form.enums.FieldTypeEnum;
import org.example.form.enums.SystemTypeEnum;
import org.example.form.exception.CustomException;
import org.example.form.field.output.TextFieldListOutput;
import org.example.form.field.output.TextFieldOutput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mongounit.AssertMatchesDataset;
import org.mongounit.MongoUnitTest;
import org.mongounit.SeedWithDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
@MongoUnitTest(name = "formtemplatecontrollertest")
public class FormTemplateControllerTest {

    @Autowired
    public FormTemplateController formTemplateController;

    @Test
    @AssertMatchesDataset
    public void createTest() throws CustomException {
        FormTemplateRequest formTemplateRequest = new FormTemplateRequest();
        formTemplateRequest.setName("teste1");
        formTemplateRequest.setType("TYPE2");
        formTemplateRequest.setSystem(SystemTypeEnum.APPLICATION1);

        formTemplateController.create(formTemplateRequest);
    }

    @Test
    @SeedWithDataset
    @AssertMatchesDataset
    public void updateTest() throws CustomException {
        FormTemplateRequest formTemplateRequest = new FormTemplateRequest();
        formTemplateRequest.setName("teste2");
        formTemplateRequest.setSystem(SystemTypeEnum.APPLICATION1);

        formTemplateController.update("6263102014023f013b1c3310", formTemplateRequest);
    }

    @Test
    @SeedWithDataset
    @AssertMatchesDataset
    public void createFieldTemplateTextFieldTest() throws CustomException {
        FieldTemplateRequest fieldTemplateRequest = new FieldTemplateRequest();
        fieldTemplateRequest.setName("Teste");
        fieldTemplateRequest.setIsRequired(true);
        fieldTemplateRequest.setIsActive(true);
        fieldTemplateRequest.setPosition(1);
        fieldTemplateRequest.setType(FieldTypeEnum.TEXT_FIELD);
        fieldTemplateRequest.setOutput(null);

        formTemplateController.createFieldTemplate("6263102014023f013b1c3310", fieldTemplateRequest);
    }

    @Test
    @SeedWithDataset
    @AssertMatchesDataset
    public void createFieldTemplateTextFieldListTest() throws CustomException {
        FieldTemplateRequest fieldTemplateRequest = new FieldTemplateRequest();
        fieldTemplateRequest.setName("Teste");
        fieldTemplateRequest.setIsRequired(true);
        fieldTemplateRequest.setIsActive(true);
        fieldTemplateRequest.setPosition(1);
        fieldTemplateRequest.setType(FieldTypeEnum.TEXT_FIELD_LIST);
        TextFieldListOutput textFieldListOutput = new TextFieldListOutput();
        textFieldListOutput.setValues(Arrays.asList("aaa", "bbb", "ccc"));
        textFieldListOutput.setIsMultiple(true);
        fieldTemplateRequest.setOutput(textFieldListOutput);

        formTemplateController.createFieldTemplate("6263102014023f013b1c3310", fieldTemplateRequest);
    }

    @Test
    @SeedWithDataset
    @AssertMatchesDataset
    public void updateFieldTemplateTextFieldTest() throws CustomException {
        FieldTemplateRequest fieldTemplateRequest = new FieldTemplateRequest();
        fieldTemplateRequest.setName("rrr");
        fieldTemplateRequest.setIsRequired(true);
        fieldTemplateRequest.setIsActive(false);
        fieldTemplateRequest.setPosition(1);
        fieldTemplateRequest.setType(FieldTypeEnum.TEXT_FIELD);
        fieldTemplateRequest.setOutput(null);

        formTemplateController.updateFieldTemplate("6263202014023f013b1c3310", "1263202014023f013b1c3310", fieldTemplateRequest);
    }

    @Test
    @SeedWithDataset
    @AssertMatchesDataset
    public void updateFieldTemplateTextFieldListTest() throws CustomException {
        FieldTemplateRequest fieldTemplateRequest = new FieldTemplateRequest();
        fieldTemplateRequest.setName("Teste1");
        fieldTemplateRequest.setIsRequired(false);
        fieldTemplateRequest.setIsActive(true);
        fieldTemplateRequest.setPosition(1);
        fieldTemplateRequest.setType(FieldTypeEnum.TEXT_FIELD_LIST);
        TextFieldListOutput textFieldListOutput = new TextFieldListOutput();
        textFieldListOutput.setValues(Arrays.asList("zzz", "bbb", "ccc"));
        textFieldListOutput.setIsMultiple(true);
        fieldTemplateRequest.setOutput(textFieldListOutput);

        formTemplateController.updateFieldTemplate("6263202014023f013b1c3310", "1263202014023f013b1c3310", fieldTemplateRequest);
    }

    @Test
    @SeedWithDataset
    @AssertMatchesDataset
    public void deleteFieldTemplateTest() throws CustomException {

        formTemplateController.deleteFieldTemplate("6263102014023f013b1c3310", "2263102014023f013b1c3310");
    }

    @Test
    @SeedWithDataset
    public void getFormTest() throws CustomException {
        String type = "TYPE";
        SystemTypeEnum systemTypeEnum = SystemTypeEnum.APPLICATION1;
        var response = formTemplateController.getForm(type, systemTypeEnum, 0);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        Assertions.assertEquals(type, response.getBody().getType());
        Assertions.assertEquals(systemTypeEnum, response.getBody().getSystem());
    }

    @Test
    public void getModelTest() {
        var response = formTemplateController.getModel();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        FieldTemplateResponse fieldDataResponseTextField = response.getBody().getFields().get(0);
        Assertions.assertEquals(fieldDataResponseTextField.getType(), FieldTypeEnum.TEXT_FIELD);
        assertTrue(fieldDataResponseTextField.getOutput() instanceof TextFieldOutput);

        FieldTemplateResponse fieldDataResponseTextFieldList = response.getBody().getFields().get(1);
        Assertions.assertEquals(fieldDataResponseTextFieldList.getType(), FieldTypeEnum.TEXT_FIELD_LIST);
        assertTrue(fieldDataResponseTextFieldList.getOutput() instanceof TextFieldListOutput);
    }
}