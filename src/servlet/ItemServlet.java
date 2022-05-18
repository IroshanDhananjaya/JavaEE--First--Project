package servlet;

import bo.BOFactory;
import bo.custom.CustomerBO;
import bo.custom.ItemBO;
import dto.CustomerDTO;
import dto.ItemDTO;

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

/**
 * @author : Dhananjaya
 * @since : 0.0.1
 **/

@WebServlet(urlPatterns = "/item")
public class ItemServlet extends HttpServlet {

    private final bo.custom.ItemBO ItemBO= (ItemBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.Item);
    @Resource(name = "java:comp/env/jdbc/pool")
    DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        try {

            JsonArrayBuilder arrayBuilder = ItemBO.loadAllItem(dataSource);


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
        String itemCode = req.getParameter("itemCode");
        String itemName = req.getParameter("itemName");
        String qty = req.getParameter("qty");
        String unitPrice = req.getParameter("unitPrice");

        resp.setContentType("application/json");
        ItemDTO dto=new ItemDTO(itemCode,itemName,qty,unitPrice);

        PrintWriter writer = resp.getWriter();

        try {
            if(ItemBO.addNewItem(dto,dataSource)){
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

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String itemCode = req.getParameter("itemCode");
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        try {
            if(ItemBO.deleteItem(itemCode,dataSource)){

                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("data","");
                objectBuilder.add("message","Successfully Deleted");
                objectBuilder.add("status",200);
                writer.print(objectBuilder.build());
            }else{

                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("data","");
                objectBuilder.add("message","something is wrong");
                objectBuilder.add("status",400);
                writer.print(objectBuilder.build());
            }
        } catch (SQLException e) {

            resp.setStatus(200);
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("data",e.getLocalizedMessage());
            objectBuilder.add("messages","Error");
            objectBuilder.add("status",500);
            writer.print(objectBuilder.build());

        } catch (ClassNotFoundException e) {

            resp.setStatus(200);
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("data",e.getLocalizedMessage());
            objectBuilder.add("messages","Error");
            objectBuilder.add("status",500);
            writer.print(objectBuilder.build());

        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();

        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();

        String code = jsonObject.getString("code");
        String name = jsonObject.getString("name");
        String qty = jsonObject.getString("qty");
        String unitPrice = jsonObject.getString("unitPrice");

        ItemDTO dto=new ItemDTO(code,name,qty,unitPrice);

        try {
            if(ItemBO.updateItem(dto,dataSource)){
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("data","");
                objectBuilder.add("message","Update Done");
                objectBuilder.add("status",200);
                writer.print(objectBuilder.build());
            }
        } catch (SQLException e) {
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("data",e.getLocalizedMessage());
            objectBuilder.add("message","Error");
            objectBuilder.add("status",500);
            writer.print(objectBuilder.build());
        } catch (ClassNotFoundException e) {
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("data",e.getLocalizedMessage());
            objectBuilder.add("message","Error");
            objectBuilder.add("status",500);
            writer.print(objectBuilder.build());
        }
    }
}
