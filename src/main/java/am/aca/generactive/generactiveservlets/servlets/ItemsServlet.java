package am.aca.generactive.generactiveservlets.servlets;

import am.aca.generactive.generactiveservlets.gen.Repository.ItemHibernateRepository;
import am.aca.generactive.generactiveservlets.gen.Repository.ItemRepository;
import am.aca.generactive.generactiveservlets.gen.jdbc_util.JDBCutil;
import am.aca.generactive.generactiveservlets.gen.model.GenerativeItem;
import am.aca.generactive.generactiveservlets.gen.model.Item;
import am.aca.generactive.generactiveservlets.gen.model.StockItem;
import am.aca.generactive.generactiveservlets.gen.util.IdGenerator;
import am.aca.generactive.generactiveservlets.gen.util.Type;
import am.aca.generactive.generactiveservlets.servlets.enums.ItemType;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.scripts.JD;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.stream.Collectors;

import static am.aca.generactive.generactiveservlets.servlets.HttpConstants.CONTENT_TYPE_JSON;

@WebServlet(name = "ItemsServlet", value = "/item/*")
public class ItemsServlet extends HttpServlet {

    public static final String PARAM_TYPE = "type";

    private final ItemHibernateRepository itemRepository = new ItemHibernateRepository();

    /**
     * Receive {@link Item} object in JSON format String.
     * Expected url param(s): {@code type}, must be one of {@link ItemType}.
     * Respond with created {@link Item} and status code {@code 200}.
     * Respond with error message and status code {@code 400}, if missing required
     * param(s), wrong {@link ItemType} string representation.
     *
     * Create Item will be saved in {@link ItemRepository}
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String typeParam = req.getParameter(PARAM_TYPE);
        resp.setContentType(CONTENT_TYPE_JSON);
        if (typeParam == null || typeParam.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Missing param: " + PARAM_TYPE);
            return;
        }

        ObjectMapper objectMapper = new ObjectMapper();

        ItemType itemType = ItemType.valueOf(typeParam);

        String payload = req.getReader().lines().collect(Collectors.joining());
        Item item;
        switch (itemType) {
            case GENERATIVE:
                item = objectMapper.readValue(payload, GenerativeItem.class);
                break;
            case STOCK:
                // FIXME
                item = objectMapper.readValue(payload, Item.class);
                break;
            default:
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("Wrong type: " + itemType);
                return;
        }

        itemRepository.insert(item);

        resp.getWriter().write(objectMapper.writeValueAsString(item));
    }

    /**
     * Get all items
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType(CONTENT_TYPE_JSON);
        ObjectMapper objectMapper = new ObjectMapper();
        resp.getWriter().write(objectMapper.writeValueAsString(itemRepository.getAllItems()));
    }
}
