package servlet;

import bo.BOFactory;
import bo.custom.ItemBO;

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

       JsonArray oId = jsonObject.getJsonArray("cart");

        System.out.println(oId);


    }
}
