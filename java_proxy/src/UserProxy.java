import javax.swing.plaf.IconUIResource;

public class UserProxy implements IUser{

    IUser user;
    public UserProxy(){

    }
    public UserProxy(IUser user){
        this.user = user;
    }
    @Override
    public void show(){
        user.show();
        System.out.println("调用了show");
    }

    @Override
    public void create() {

    }

    @Override
    public void update() {

    }
}
