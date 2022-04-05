package cc.blbana.Basic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;

public class FJUser {
    public static void main(String[] args) {
        //ParserConfig.getGlobalInstance().addAccept("com.taobao.pac.client.sdk.dataobject.");
        //ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        String userString = "{\"@type\":\"cc.blbana.TemplatesImpl.Fastjson.User\" ,\"age\":18,\"name\":\"blbana\",\"address\": \"xian\", \"sex\": \"man\", \"properties\": {}}";
        User user = JSON.parseObject(userString, User.class, Feature.SupportNonPublicField);
//        Object user = JSON.parseObject(userString, Object.class, Feature.SupportNonPublicField);
        System.out.println(user);
        System.out.println(user.getClass().getName());
    }
}

