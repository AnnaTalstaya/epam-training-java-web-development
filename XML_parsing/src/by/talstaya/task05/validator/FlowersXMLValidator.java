package by.talstaya.task05.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

public class FlowersXMLValidator {

    private static final Logger LOGGER = LogManager.getLogger("name");

    public boolean validFile(String fileName) {
        String schemaFile = "data/greenhouse_schema.xsd";

        try {
            SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                    .newSchema(new File(schemaFile))
                    .newValidator().validate(new StreamSource(new File(fileName)));
        } catch (SAXException | IOException e) {
            LOGGER.error(e);
            return false;
        }

        LOGGER.info("File " + fileName + " is valid.");

        return true;
    }
}
