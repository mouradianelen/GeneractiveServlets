package am.aca.generactive.generactiveservlets.gen.orm;

import am.aca.generactive.generactiveservlets.gen.model.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConfiguration {
    private final SessionFactory factory;
    private static HibernateConfiguration sInstance;

    public static HibernateConfiguration getInstance(){
        if (sInstance==null)
            sInstance=new HibernateConfiguration();
        return sInstance;
    }

    private HibernateConfiguration(){
        factory=new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Item.class)
                .buildSessionFactory();
    }

    public Session getSession(){
        return factory.getCurrentSession();
    }
}
