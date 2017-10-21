/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import FunctionLayer.LoginSampleException;
import FunctionLayer.Order;
import FunctionLayer.Pieces;
import FunctionLayer.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

public class OrderMapper {
    public static void createOrder(Pieces pieces, User user) throws LoginSampleException 
    {
        try {
            Connection con = Connector.connection();
            String SQL = "insert into useradmin.order(orderHeight, orderLength, orderWidth,  userID) values(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement( SQL, Statement.RETURN_GENERATED_KEYS );
            ps.setInt(1, pieces.getWidth());
            ps.setInt(2, pieces.getLength());
            ps.setInt(3, pieces.getHeight());
            ps.setInt(4, user.getId());
            ps.executeUpdate();
        } catch ( SQLException | ClassNotFoundException ex ) {
            throw new LoginSampleException( ex.getMessage() );
        }
    }
    
    public static List<Order> getOrders(int id) throws LoginSampleException
    {
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT orderID, orderDate, orderWidth, orderLength, orderHeight FROM `Order` WHERE userID= ? ORDER BY orderID DESC";
            PreparedStatement ps = con.prepareStatement( SQL, Statement.RETURN_GENERATED_KEYS );
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            List<Order> orders = new ArrayList<>();
            while(rs.next())
            {
                orders.add(new Order
                    (rs.getInt("orderID")
                    , rs.getString("orderDate")
                    , new Pieces
                        (rs.getInt("orderWidth")
                        , rs.getInt("orderLength")
                        , rs.getInt("orderHeigth")
                        )
                    )
                );
            }
            return orders;
        } catch ( SQLException | ClassNotFoundException ex ) {
            throw new LoginSampleException( ex.getMessage() );
        }
    }
    
    public static List<Order> getOrders() throws LoginSampleException
    {
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT orderID, orderDate, orderWidth, orderLength, orderHeight FROM `Order` WHERE userID= ? ORDER BY orderID DESC";
            PreparedStatement ps = con.prepareStatement( SQL, Statement.RETURN_GENERATED_KEYS );
            ResultSet rs = ps.executeQuery();
            List<Order> orders = new ArrayList<>();
            while(rs.next())
            {
                orders.add(new Order
                    (rs.getInt("orderID")
                    , rs.getString("orderDate")
                    , new Pieces
                        (rs.getInt("orderWidth")
                        , rs.getInt("orderLength")
                        , rs.getInt("orderHeigth")
                        )
                    )
                );
            }
            return orders;
        } catch ( SQLException | ClassNotFoundException ex ) {
            throw new LoginSampleException( ex.getMessage() );
        }
    }
    
    public static Order getOrder(int id) throws LoginSampleException
    {
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT orderID, orderDate, orderWidth, orderLength, orderHeight FROM `Order` WHERE userID= ? ORDER BY orderID DESC";
            PreparedStatement ps = con.prepareStatement( SQL, Statement.RETURN_GENERATED_KEYS );
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                return new Order
                    (rs.getInt("orderID")
                    , rs.getString("orderDate")
                    , new Pieces
                        (rs.getInt("orderWidth")
                        , rs.getInt("orderLength")
                        , rs.getInt("orderHeigth")
                        )
                    );
            }
            return null;
        } catch ( SQLException | ClassNotFoundException ex ) {
            throw new LoginSampleException( ex.getMessage() );
        }
    }
    
    public static void updateOrder(List<String> colm, List<String> values, int id) throws LoginSampleException
    {
        try {
            Connection con = Connector.connection();
            String SQL = "UPDATE `Order` SET";
            for(int i = 0; i < colm.size(); i++)
            {
                if(i == 0)
                    SQL += colm.get(i) + " = ?, ";
                else if(i == 1)
                    SQL += colm.get(i) + " = ?";
                else
                    SQL += ", " + colm.get(i);
            }
            SQL += " WHERE orderID = ?"; 
            PreparedStatement ps = con.prepareStatement( SQL, Statement.RETURN_GENERATED_KEYS );
            for(int i = 0; i < values.size(); i++)
            {
                if(values.get(i).matches("[0-9]+"))
                    ps.setInt(i, Integer.parseInt(values.get(i)));
                else
                    ps.setString(i, values.get(i));
            }
            ps.setInt(values.size()+1, id);
            ps.executeUpdate();
        } catch ( SQLException | ClassNotFoundException ex ) {
            throw new LoginSampleException( ex.getMessage() );
        }
    }
}
