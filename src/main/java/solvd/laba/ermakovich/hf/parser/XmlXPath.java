package solvd.laba.ermakovich.hf.parser;


import com.jcabi.xml.XMLDocument;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.util.ResourceUtils;


/**
 * @author Ermakovich Kseniya
 */

@RequiredArgsConstructor
public class XmlXPath {

    private final String filePath;
    private final String tagName;

    @SneakyThrows
    public String getText() {
        return new XMLDocument(
                ResourceUtils.getFile(filePath))
                .xpath("//" + tagName + "/text()")
                .get(0);
    }

}
