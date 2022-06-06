package org.example.hexagonal.adapters.outbound;

import lombok.RequiredArgsConstructor;
import org.example.hexagonal.adapters.outbound.client.TagClient;
import org.example.hexagonal.application.core.domain.Tag;
import org.example.hexagonal.application.ports.out.FindTagOutPort;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FindTagOutAdapter implements FindTagOutPort {

    private final TagClient tagClient;

    @Override
    public List<Tag> find(String bookName) {
        //TagClient.findByBookName(bookName).getBody();
        //Mock
        Tag tag = new Tag();
        tag.setName("Test");
        return Arrays.asList(tag);
    }
}
