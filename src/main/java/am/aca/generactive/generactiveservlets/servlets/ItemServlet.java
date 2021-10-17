//package am.aca.generactive.generactiveservlets.servlets;
//
//import am.aca.generactive.generactiveservlets.gen.Repository.ItemHibernateRepository;
//import am.aca.generactive.generactiveservlets.gen.Repository.ItemJdbcRepository;
//import am.aca.generactive.generactiveservlets.gen.Repository.ItemRepository;
//import am.aca.generactive.generactiveservlets.gen.model.GenerativeItem;
//import am.aca.generactive.generactiveservlets.gen.model.Item;
//import am.aca.generactive.generactiveservlets.gen.model.StockItem;
//import am.aca.generactive.generactiveservlets.gen.orm.HibernateConfiguration;
//import am.aca.generactive.generactiveservlets.servlets.enums.ItemType;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.stream.Collectors;
//
//import static am.aca.generactive.generactiveservlets.servlets.HttpConstants.CONTENT_TYPE_JSON;
//
//@WebServlet(name = "ItemsServlet", urlPatterns = "/items/*")
//public class ItemServlet extends HttpServlet {
//    public static final String PARAM_TYPE = "type";
//
//    private final ItemRepository itemRepository = ItemRepository.getInstance();
//    private final ItemJdbcRepository itemJdbcRepository = new ItemJdbcRepository();
////    private final ItemHibernateRepository itemHibernateRepository=ItemHibernateRepository.getInstance();
//
//    /**
//     * Receive {@link Item} object in JSON format String.
//     * Expected url param(s): {@code type}, must be one of {@link ItemType}.
//     * Respond with created {@link Item} and status code {@code 200}.
//     * Respond with error message and status code {@code 400}, if missing required
//     * param(s), wrong {@link ItemType} string representation.
//     *
//     * Create Item will be saved in {@link ItemRepository}
//     */
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String typeParam = req.getParameter(PARAM_TYPE);
//        resp.setContentType(CONTENT_TYPE_JSON);
//        if (typeParam == null || typeParam.isEmpty()) {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            resp.getWriter().write("Missing param: " + PARAM_TYPE);
//            return;
//        }
//
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        ItemType itemType = ItemType.valueOf(typeParam);
//
//        String payload = req.getReader().lines().collect(Collectors.joining());
//        Item item;
//        switch (itemType) {
//            case GENERATIVE:
//                item = objectMapper.readValue(payload, GenerativeItem.class);
//                break;
//            case STOCK:
//                item = objectMapper.readValue(payload, StockItem.class);
//                break;
//            default:
//                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//                resp.getWriter().write("Wrong type: " + itemType);
//                return;
//        }
//
//        // item.setId(IdGenerator.getNext(Type.ITEM));
//        // itemRepository.addItem(item);
////        long id = itemHibernateRepository.insert(item);
////        item.setId((int) id);
//
//        resp.getWriter().write(objectMapper.writeValueAsString(item));
//    }
//
//    /**
//     * Get all items
//     */
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        resp.setContentType(CONTENT_TYPE_JSON);
//        ObjectMapper objectMapper = new ObjectMapper();
//        resp.getWriter().write(objectMapper.writeValueAsString(itemJdbcRepository.getAllItems()));
//    }
//}
