package core.azure.model.attachment;


import core.azure.dates.DatePicker;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class Attachment {

    private String stream;
    private String fileName;
    private String comment;
    private String attachmentType;

    public Attachment() {
        this.stream = "";
        this.fileName = String.format("Screeshot_%s_%s.png",
                DatePicker.getCurrentDate().replace("/",""),
                DatePicker.getCurrentTime().replace(":",""));
        this.comment = "Screenshot  anexada via teste automatizado";
        this.attachmentType = "GeneralAttachment";
    }

    public Attachment(String comment) {
        this.stream = "";
        this.fileName = String.format("Screeshot_%s_%s.png",
                DatePicker.getCurrentDate().replace("/",""),
                DatePicker.getCurrentTime().replace(":",""));
        this.comment = comment;
        this.attachmentType = "GeneralAttachment";
    }
}
