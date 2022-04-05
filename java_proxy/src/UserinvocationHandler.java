import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class UserinvocationHandler implements InvocationHandler {
    IUser user;
    public UserinvocationHandler()
    {

    }
    public UserinvocationHandler(IUser user)
    {
        this.user = user;

    }
    @Override
    //不管调用什么,都会调用invoke,所以调用的时候有动态代理可以自动执行
    public Object invoke(Object proxy, Method method,Object[] args) throws  Throwable{
        System.out.println("调用了"+method);
        method.invoke(user,args);
        return null;
    }
}
