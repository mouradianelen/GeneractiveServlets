package am.aca.generactive.generactiveservlets.gen.Repository;

import am.aca.generactive.generactiveservlets.gen.model.Item;
import am.aca.generactive.generactiveservlets.gen.orm.HibernateConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ItemHibernateRepository {

    private static HibernateConfiguration HIBERNATE_CONFIGURSTION = HibernateConfiguration.getInstance();
    private static ItemHibernateRepository sInstance;
    public static ItemHibernateRepository getInstance() {
        if (sInstance == null) {
            sInstance = new ItemHibernateRepository();
        }

        return sInstance;
    }
    public  ItemHibernateRepository () {

    }

    public long insert(Item item) {

        Session session = HIBERNATE_CONFIGURSTION.getSession();
        Transaction tx = session.beginTransaction();
        session.save(item);
        tx.commit();

        return 0;
    }
}
