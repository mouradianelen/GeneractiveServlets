package am.aca.generactive.generactiveservlets.gen.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Basket implements FindHighestPricedItems, FindByName,FindById {
    private final List<BasketItem> basketItems = new ArrayList<>();

    public void add(BasketItem basketItem) {
        basketItems.add(basketItem);
    }

    public boolean remove(BasketItem basketItem) {
        return basketItems.remove(basketItem);
    }

    public void print() {
        System.out.println("Basket ----- ");
        for(BasketItem basketItem : basketItems) {
            basketItem.print();
        }

        System.out.printf("Total price: %d $", getTotalPrice());
    }

    public int getTotalPrice() {
        int sum = 0;

        for(BasketItem basketItem : basketItems) {
            sum += basketItem.getPrice();
        }

        return sum;
    }

    @Override
    public List<BasketItem> findHighestPricedItems(){
        BasketItem item=basketItems.stream()
                .max(Comparator.comparing(BasketItem::getPrice))
                .orElseThrow(NoSuchElementException::new);
        return basketItems
                .stream()
                .filter(c -> c.getPrice() == item.getPrice())
                .collect(Collectors.toList());

    }

    @Override
    public List<BasketItem> findByName(String str) {
        return basketItems.stream()
                .filter(c->c.getItem().getName().startsWith(str))
                .collect(Collectors.toList());


    }

    @Override
    public List<BasketItem> findById(int id) {
        return basketItems.stream()
                .filter(c->c.getItem().getId()==id)
                .collect(Collectors.toList());

    }
}
