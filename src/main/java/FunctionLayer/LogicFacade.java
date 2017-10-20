package FunctionLayer;

import DBAccess.OrderMapper;
import DBAccess.UserMapper;
import java.util.List;

public class LogicFacade {

    public static User login( String email, String password ) throws LoginSampleException {
        return UserMapper.login( email, password );
    } 

    public static User createUser( String email, String password ) throws LoginSampleException {
        User user = new User(email, password, "customer");
        UserMapper.createUser( user );
        return user;
    }
    public static void createOrder(Pieces pieces, User user) throws LoginSampleException
    {
        OrderMapper.createOrder(pieces, user);
    }
    
    public static List<Order> getOrders(int id) throws LoginSampleException
    {
        return OrderMapper.getOrders(id);
    }
    
    public static List<Order> getOrders() throws LoginSampleException
    {
        return OrderMapper.getOrders();
    }
    
    public static Order getOrder(int id) throws LoginSampleException
    {
        return OrderMapper.getOrder(id);
    }
    
    public static void updateOrder(List<String> colm, List<String> values, int id) throws LoginSampleException
    {
        OrderMapper.updateOrder(colm, values, id);
    }
    

}
