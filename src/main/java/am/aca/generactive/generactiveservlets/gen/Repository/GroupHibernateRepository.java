//package am.aca.generactive.generactiveservlets.gen.Repository;
//
//import am.aca.generactive.generactiveservlets.gen.model.Group;
//import am.aca.generactive.generactiveservlets.gen.model.Item;
//import am.aca.generactive.generactiveservlets.gen.orm.HibernateConfiguration;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import org.hibernate.query.Query;
//
//import java.util.List;
//import java.util.Optional;
//
//public class GroupHibernateRepository {
//    public static final HibernateConfiguration HIBERNATE_CONFIGURATION =
//            HibernateConfiguration.getInstance();
//
//    public Group insert(Group group) {
//        Session session = HIBERNATE_CONFIGURATION.getSession();
//        Transaction transaction = session.beginTransaction();
//
//
//        session.save(group);
//
//        transaction.commit();
//
//        session.close();
//
//        return group;
//    }
//
//    public Group update(Group group) {
//        Session session = HIBERNATE_CONFIGURATION.getSession();
//        Transaction transaction = session.beginTransaction();
//
//        Group existing = session.get(Group.class, group.getId());
//        existing.setName(group.getName());
//        existing.setItems(group.getItems());
//
//        transaction.commit();
//
//        session.close();
//
//        return existing;
//    }
//
//    public List<? extends Group> getAllGroups() {
//        Session session = HIBERNATE_CONFIGURATION.getSession();
//
//        Transaction transaction = session.beginTransaction();
//
//        String q = "from Group g";
//        javax.persistence.Query query = session.createQuery(q, Group.class);
//
//        List<? extends Group> groups = query.getResultList();
//
//        transaction.commit();
//        session.close();
//
//        return groups;
//    }
//
//    public Optional<Group> getGroup(long groupId) {
//        Session session = HIBERNATE_CONFIGURATION.getSession();
//        Transaction transaction = session.beginTransaction();
//
//
//        Query<Group> query = session.createQuery(
//                "select g from Group g" +
//                        " where g.id = :id ",
//                Group.class);
//        query.setParameter("id", groupId);
//
//        Group group = query.getSingleResult();
//
//        transaction.commit();
//        session.close();
//
//        return Optional.ofNullable(group);
//    }
//}
