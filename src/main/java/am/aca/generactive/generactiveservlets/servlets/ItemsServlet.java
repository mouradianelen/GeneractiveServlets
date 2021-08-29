package am.aca.generactive.generactiveservlets.servlets;

import am.aca.generactive.generactiveservlets.gen.Repository.ItemRepository;
import am.aca.generactive.generactiveservlets.gen.model.GenerativeItem;
import am.aca.generactive.generactiveservlets.gen.model.Item;
import am.aca.generactive.generactiveservlets.gen.model.StockItem;
import am.aca.generactive.generactiveservlets.gen.util.IdGenerator;
import am.aca.generactive.generactiveservlets.gen.util.Type;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

@WebServlet(name = "ItemsServlet", value = "/item/*")
public class ItemsServlet extends HttpServlet {
    public static final String PARAM_TYPE = "type";
    public final ItemRepository items = ItemRepository.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String typeParam = req.getParameter(PARAM_TYPE);
        ObjectMapper objectMapper = new ObjectMapper();
        resp.setContentType("application/json");
        if (typeParam == null || typeParam.isEmpty()) {
            resp.setStatus(400);
            resp.getWriter().write("Missing param " + PARAM_TYPE);
            return;
        }
        ItemType itemType = ItemType.valueOf(typeParam);
        BufferedReader bufferedReader = req.getReader();
        String payload = bufferedReader.lines().collect(Collectors.joining());
        Item item;

        switch (itemType) {
            case GENERATIVE:
                item = objectMapper.readValue(payload, GenerativeItem.class);
                break;
            case STOCK:
                item = objectMapper.readValue(payload, StockItem.class);
                break;
            default:

                return;

        }
        item.setId(IdGenerator.getNext(Type.ITEM));
        items.addItem(item);
        resp.getWriter().write(objectMapper.writeValueAsString(item));

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IOException {
        System.out.println(req.getPathInfo());
        ObjectMapper objectMapper = new ObjectMapper();
        String response = objectMapper.writeValueAsString(items.getList());
        System.out.println(items.getSize());
        PrintWriter writer = resp.getWriter();
        writer.write(response);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(String.valueOf(req.getPathInfo().charAt(1)));
        items.deleteItemById(id);
        System.out.print(items.getSize());

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(String.valueOf(req.getPathInfo().charAt(1)));
        String typeParam = req.getParameter(PARAM_TYPE);
        ObjectMapper objectMapper = new ObjectMapper();
        resp.setContentType("application/json");
        if (typeParam == null || typeParam.isEmpty()) {
            resp.setStatus(400);
            resp.getWriter().write("Missing param " + PARAM_TYPE);
            return;
        }
        ItemType itemType = ItemType.valueOf(typeParam);
        BufferedReader bufferedReader = req.getReader();
        String payload = bufferedReader.lines().collect(Collectors.joining());
        Item item;

        switch (itemType) {
            case GENERATIVE:
                item = objectMapper.readValue(payload, GenerativeItem.class);
                break;
            case STOCK:
                item = objectMapper.readValue(payload, StockItem.class);
                break;
            default:

                return;

        }
        items.replace(id, item);
        System.out.println(item.getId() + item.getName());
    }

    public enum ItemType {
        GENERATIVE, STOCK
    }
}