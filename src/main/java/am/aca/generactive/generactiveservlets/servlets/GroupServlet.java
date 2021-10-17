package am.aca.generactive.generactiveservlets.servlets;

//import am.aca.generactive.generactiveservlets.gen.Repository.GroupHibernateRepository;
//import am.aca.generactive.generactiveservlets.gen.Repository.ItemHibernateRepository;
//import am.aca.generactive.generactiveservlets.gen.model.GenerativeItem;
//import am.aca.generactive.generactiveservlets.gen.model.Group;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.stream.Collectors;
//
//import static am.aca.generactive.generactiveservlets.servlets.HttpConstants.CONTENT_TYPE_JSON;
//@WebServlet(name = "GroupServlet", value = "/group")
//public class GroupServlet extends HttpServlet {
//    private final GroupHibernateRepository groupRepository = new GroupHibernateRepository();
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType(CONTENT_TYPE_JSON);
//        ObjectMapper objectMapper = new ObjectMapper();
//        Group group;
//        String payload = req.getReader().lines().collect(Collectors.joining());
//        group = objectMapper.readValue(payload, Group.class);
//        groupRepository.insert(group);
//        resp.getWriter().write(objectMapper.writeValueAsString(group));
//
//    }
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        resp.setContentType(CONTENT_TYPE_JSON);
//        ObjectMapper objectMapper = new ObjectMapper();
//        resp.getWriter().write(objectMapper.writeValueAsString(groupRepository.getAllGroups()));
//    }
//}
