package core;

import core.azure.model.attachment.Attachment;
import core.config.Configuration;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigCache;

import java.io.PrintStream;
import java.io.StringWriter;
import java.util.Collection;

public class Spec {

    public static RequestSpecification spec;
    public static Collection<Attachment> attachments;
    public static StringWriter requestWriter;
    public static PrintStream requestCapture;
    public static StringWriter responseWriter;
    public static PrintStream responseCapture;
    public static final Configuration configuration = ConfigCache.getOrCreate(Configuration.class);
}
