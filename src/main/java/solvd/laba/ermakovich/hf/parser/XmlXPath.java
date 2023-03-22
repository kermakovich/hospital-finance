package solvd.laba.ermakovich.hf.parser;


import com.jcabi.xml.XMLDocument;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.ResourceLoader;


/**
 * @author Ermakovich Kseniya
 */

@RequiredArgsConstructor
public class XmlXPath {

    private final String filePath;
    private final String tagName;
    private final ResourceLoader resourceLoader;


    @SneakyThrows
    public String getText() {
        return new XMLDocument(
                resourceLoader.getResource(filePath).getInputStream())
                .xpath("//" + tagName + "/text()")
                .get(0);
    }

}
