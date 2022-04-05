import com.sun.org.apache.bcel.internal.generic.IUSHR;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args){
        IUser user = new UserImpl();
//        user.show();
// 静态代理，缺点，如果方法多要写的就多了。
//        IUser userProxy = new UserProxy(user);
//        userProxy.show();


        //动态代理
        //代理接口，要做的事情，classloader
        InvocationHandler userinvocationhandeler = new UserinvocationHandler(user);
        IUser userProxy = (IUser)Proxy.newProxyInstance(user.getClass().getClassLoader(), user.getClass().getInterfaces(),userinvocationhandeler);
        userProxy.update();
    }


}
