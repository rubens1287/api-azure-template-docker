package core.azure.model.attachment;


import com.google.gson.JsonParser;
import core.azure.dates.DatePicker;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.binary.Base64;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.stream.IntStream;


@Getter @Setter
public class Attachment {

    private String stream;
    private String fileName;
    private String comment;
    private String attachmentType;

    public Attachment(String value) {
        this.stream = new String(Base64.encodeBase64(value.getBytes()));
        this.fileName = String.format("Evidence_%s_%s.json",
                DatePicker.getCurrentDate().replace("/",""),
                DatePicker.getCurrentTime().replace(":",""));
        this.comment = "Evidência anexada via teste automatizado";
        this.attachmentType = "GeneralAttachment";
    }

    public Attachment(String value,String comment) {
        this.stream = new String(Base64.encodeBase64(value.getBytes()));
        this.fileName = String.format("Evidence_%s_%s.json",
                DatePicker.getCurrentDate().replace("/",""),
                DatePicker.getCurrentTime().replace(":",""));
        this.comment = comment;
        this.attachmentType = "GeneralAttachment";
    }
}
