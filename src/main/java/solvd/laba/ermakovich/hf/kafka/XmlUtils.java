package solvd.laba.ermakovich.hf.kafka;


import com.jcabi.xml.XML;
import com.jcabi.xml.XMLDocument;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.springframework.util.ResourceUtils;


/**
 * @author Ermakovich Kseniya
 */

@UtilityClass
public class XmlUtils {

    @SneakyThrows
    public String getValue(final String filePath, final String s) {
        final XML settings;
        settings = new XMLDocument(
                ResourceUtils.getFile(filePath)
        );
        return settings.xpath("//" + s + "/text()").get(0);
    }

}
