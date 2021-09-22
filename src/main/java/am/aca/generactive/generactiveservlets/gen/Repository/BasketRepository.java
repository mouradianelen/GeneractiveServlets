package am.aca.generactive.generactiveservlets.gen.Repository;

import am.aca.generactive.generactiveservlets.gen.model.Basket;
import am.aca.generactive.generactiveservlets.gen.orm.HibernateConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Optional;

public class BasketRepository {
    public Optional<Basket> getBasket(Long basketId) {
        Session session = HibernateConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Basket basket = session.get(Basket.class, basketId);

        transaction.commit();
        session.close();

        return Optional.ofNullable(basket);
    }
}
