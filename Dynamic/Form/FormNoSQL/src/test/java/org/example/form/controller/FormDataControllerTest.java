package org.example.form.controller;

import org.example.form.Application;
import org.example.form.dto.request.FieldDataRequest;
import org.example.form.dto.request.FormDataRequest;
import org.example.form.dto.response.FieldDataResponse;
import org.example.form.enums.FieldTypeEnum;
import org.example.form.exception.CustomException;
import org.example.form.field.input.TextFieldInput;
import org.example.form.field.input.TextFieldListInput;
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
@MongoUnitTest(name = "formdatacontrollertest")
public class FormDataControllerTest {

    @Autowired
    public FormDataController formDataController;

    @Test
    @SeedWithDataset
    @AssertMatchesDataset
    public void createTest() throws CustomException {

        FieldDataRequest fieldDataRequest1 = new FieldDataRequest();
        fieldDataRequest1.setFieldTemplateId("626596f6fc3e480abbeecd4b");
        TextFieldInput textFieldInput = new TextFieldInput();
        textFieldInput.setValue("Teste");
        fieldDataRequest1.setInput(textFieldInput);

        FieldDataRequest fieldDataRequest2 = new FieldDataRequest();

        fieldDataRequest2.setFieldTemplateId("62659817fc3e480abbeecd4f");
        TextFieldListInput textFieldListInput = new TextFieldListInput();
        textFieldListInput.setValues(Arrays.asList("Teste", "Test"));
        fieldDataRequest2.setInput(textFieldListInput);

        FormDataRequest formDataRequest = new FormDataRequest();
        formDataRequest.setFieldsData(Arrays.asList(fieldDataRequest1, fieldDataRequest2));

        formDataController.create("6263102014023f013b1c3310", formDataRequest);
    }

    @Test
    @SeedWithDataset
    @AssertMatchesDataset
    public void updateTest() throws CustomException {

        FieldDataRequest fieldDataRequest1 = new FieldDataRequest();
        fieldDataRequest1.setFieldTemplateId("626596f6fc3e480abbeecd4b");
        TextFieldInput textFieldInput = new TextFieldInput();
        textFieldInput.setValue("T");
        fieldDataRequest1.setInput(textFieldInput);

        FieldDataRequest fieldDataRequest2 = new FieldDataRequest();

        fieldDataRequest2.setFieldTemplateId("62659817fc3e480abbeecd4f");
        TextFieldListInput textFieldListInput = new TextFieldListInput();
        textFieldListInput.setValues(Arrays.asList("Teste"));
        fieldDataRequest2.setInput(textFieldListInput);

        FormDataRequest formDataRequest = new FormDataRequest();
        formDataRequest.setFieldsData(Arrays.asList(fieldDataRequest1, fieldDataRequest2));

        formDataController.update("62659817fc3e480abbaecd4f", formDataRequest);
    }

    @Test
    @SeedWithDataset
    public void getByIdTest() throws CustomException {
        var reponse = formDataController.getById("62659817fc3e480abbaecd4f");
        assertEquals(HttpStatus.OK, reponse.getStatusCode());
        assertNotNull(reponse.getBody());
    }

    @Test
    public void getModel() {
        var reponse = formDataController.getModel();
        assertEquals(HttpStatus.OK, reponse.getStatusCode());
        assertNotNull(reponse.getBody());
        assertEquals(reponse.getBody().getFields().size(), 2);

        FieldDataResponse fieldDataResponseTextField = reponse.getBody().getFields().get(0);
        Assertions.assertEquals(fieldDataResponseTextField.getType(), FieldTypeEnum.TEXT_FIELD);
        assertTrue(fieldDataResponseTextField.getInput() instanceof TextFieldInput);
        assertTrue(fieldDataResponseTextField.getOutput() instanceof TextFieldOutput);

        FieldDataResponse fieldDataResponseTextFieldList = reponse.getBody().getFields().get(1);
        assertEquals(fieldDataResponseTextFieldList.getType(), FieldTypeEnum.TEXT_FIELD_LIST);
        assertTrue(fieldDataResponseTextFieldList.getInput() instanceof TextFieldListInput);
        assertTrue(fieldDataResponseTextFieldList.getOutput() instanceof TextFieldListOutput);
    }
}
