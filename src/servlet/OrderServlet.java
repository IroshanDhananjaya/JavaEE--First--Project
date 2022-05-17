package servlet;

import bo.BOFactory;
import bo.custom.ItemBO;
import dto.OrderDTO;
import dto.OrderDetailsDTO;
import entity.Order;

import javax.annotation.Resource;
import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Dhananjaya
 * @since : 0.0.1
 **/

@WebServlet(urlPatterns = "/order")
public class OrderServlet extends HttpServlet {

    private final bo.custom.OrderBO orderBO= (bo.custom.OrderBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.Order);
    @Resource(name = "java:comp/env/jdbc/pool")
    DataSource dataSource;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        try {

            JsonArrayBuilder arrayBuilder =orderBO.loadAllOrder(dataSource);


            PrintWriter writer = resp.getWriter();
            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("massage","Done");
            response.add("status",200);
            response.add("data",arrayBuilder.build());


            writer.print(response.build());

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();

        String orderID = jsonObject.getString("orderID");
        String orderDate = jsonObject.getString("orderDate");
        String custId = jsonObject.getString("custId");
        String ototal = jsonObject.getString("Ototal");
        JsonArray cart = jsonObject.getJsonArray("cart");
        ArrayList<OrderDetailsDTO>detailsDTOS=new ArrayList<>();;


        for(int i=0;i<cart.size();i++){

            JsonObject details = cart.getJsonObject(i);
            String oid = details.getString("oid");
            String cName = details.getString("cName");
            String iID = details.getString("iID");
            String iName = details.getString("iName");
            String iPrice = details.getString("iPrice");
            String orderQty = details.getString("orderQty");
            String ordertotal = details.getString("Ordertotal");





       detailsDTOS.add(new OrderDetailsDTO(oid,iID,Integer.parseInt(orderQty),Double.parseDouble(iPrice),Double.parseDouble(ordertotal)));

        }

        for (OrderDetailsDTO temp:detailsDTOS) {
            System.out.println(temp.getItemCode()+"Huuuuu");
        }

        OrderDTO orderDTO=new OrderDTO(orderID,orderDate,custId,ototal,detailsDTOS);

        try {
            if(orderBO.placeOrder(orderDTO,dataSource)){
                JsonObjectBuilder response = Json.createObjectBuilder();
                resp.setStatus(HttpServletResponse.SC_CREATED);//201
                response.add("status", 200);
                response.add("message", "Done");
                response.add("data", "Successfully Added");
                writer.print(response.build());
            }
        } catch (SQLException e) {
            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("status", 400);
            response.add("message", "Error");
            response.add("data", e.getLocalizedMessage());
            writer.print(response.build());
        } catch (ClassNotFoundException e) {
            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("status", 400);
            response.add("message", "Error");
            response.add("data", e.getLocalizedMessage());
            writer.print(response.build());
        }




    }
}
