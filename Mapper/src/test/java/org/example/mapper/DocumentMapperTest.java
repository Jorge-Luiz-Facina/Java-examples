package org.example.mapper;

import org.example.dto.DocumentDTO;
import org.example.entity.DocumentEntity;
import org.example.entity.OtherEntity;
import org.example.util.RandomValueUtil;
import org.example.util.ReflectionFieldUtil;
import org.example.util.model.SelectField;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DocumentMapperTest {


    @Test
    public void userEntitysToUserDTOs_NoNull_Ok_Test() {
        var documentEntities = RandomValueUtil.generateList(DocumentEntity.class);
        var documentDTOs = DocumentMapper.INSTANCE.documentEntitysToDocumentDTOs(documentEntities);
        ReflectionFieldUtil.verifyFields(documentDTOs.get(0));
    }

    @Test
    public void UserDTOToUserEntity_NoNull_Ok_Test() {
        var documentDTO = RandomValueUtil.generate(DocumentDTO.class);
        OtherEntity otherEntity = new OtherEntity();
        var documentEntity = DocumentMapper.INSTANCE.documentDTOToDocumentEntity(documentDTO, otherEntity);
        ReflectionFieldUtil.verifyFields(documentEntity, Arrays.asList(new SelectField(DocumentEntity.class, Arrays.asList("codeWWW"), false)));
    }

    @Test
    public void UserDTOMergeToUserEntity_Ok_Test() {
        var documentDTOs = RandomValueUtil.generate(DocumentDTO.class);
        var documentEntity = new DocumentEntity(null, null, null, null);
        DocumentMapper.INSTANCE.documentDTOMergeToDocumentEntity(documentDTOs, documentEntity);
        ReflectionFieldUtil.verifyFields(documentEntity, Arrays.asList(new SelectField(DocumentEntity.class, Arrays.asList("codeWWW", "other"), false)));
    }

    @Test
    public void UserDTOUpdateToUserEntity_Ok_Test() {
        var documentDTOs = new DocumentDTO(null, "test");
        var documentEntity = new DocumentEntity("1", "2", "3", null);
        DocumentMapper.INSTANCE.documentDTOUpdateToDocumentEntity(documentDTOs, documentEntity);
        assertEquals("test", documentEntity.getCode());
        assertEquals("1", documentEntity.getTypeName());
    }

    @Test
    public void cloneDocumentEntity_Ok_Test() {
        var documentEntity = new DocumentEntity("null", "null", "NULL", null);
        var documentEntityClone = DocumentMapper.INSTANCE.cloneDocumentEntity(documentEntity);
        documentEntityClone.setCode("test");
        assertNotEquals(documentEntityClone.getCode(), documentEntity.getCode());
        assertEquals(documentEntityClone.getTypeName(), documentEntity.getTypeName());
    }

}
