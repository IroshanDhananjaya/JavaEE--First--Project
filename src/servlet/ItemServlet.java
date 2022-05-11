package servlet;

import bo.BOFactory;
import bo.custom.CustomerBO;
import bo.custom.ItemBO;
import dto.CustomerDTO;
import dto.ItemDTO;

import javax.annotation.Resource;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
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
}
