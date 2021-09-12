package am.aca.generactive.generactiveservlets.gen.Repository;


import am.aca.generactive.generactiveservlets.gen.model.Group;
import am.aca.generactive.generactiveservlets.gen.model.Item;
import am.aca.generactive.generactiveservlets.gen.model.StockItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemRepository {
    private static ItemRepository sInstance;

    private final List<Item> items = new ArrayList<>();
    private String url = "jdbc:postgresql://localhost:5432/postgres";
    private String user = "postgres";
    private String password = "12345";

    public static ItemRepository getInstance() {
        if (sInstance == null) {
            sInstance = new ItemRepository();
        }

        return sInstance;
    }

    public Item addItem(Item item) {
        this.items.add(item);
        return item;
    }

    public void deleteItemById(int id) {
        items.remove(findItemById(id));
    }

    public void clear() {
        items.clear();
    }

    public void addItemAll(List<Item> items) {
        this.items.addAll(items);
    }

    public Item findItemById(int Id) {

        for (Item item : items) {
            if (item.getId() == Id) {
                return item;
            }
        }
        return null;
    }

    public List<Item> findItemByPrice(int priceFrom, int priceTo) {
        List<Item> list = new ArrayList<>();
        for (Item item : items) {
            if (item.getBasePrice() < priceTo && item.getBasePrice() > priceFrom) {
                System.out.println(item.getId() + item.getName());
                list.add(item);
            }
        }
        return list;
    }

    public void replace(int id, Item item) {
        items.set(id, item);
    }

    public List<Item> getList() {
        return this.items;
    }

    public Item existsById(int id) {
        for (Item item : items) {
            if (item.getId() == id)
                if (item != null)
                    return item;
        }
        return null;
    }

    public List<Group> getGroups() {
        List<Group> parents = new ArrayList<>();

        for (Item item : items) {
            if (item.getGroup() == null) {
                parents.add(item.getGroup());
            }
        }

        return parents;
    }

    private ItemRepository() {

    }

    public int getSize() {
        return this.items.size();
    }

    public List<Item> findAll() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection connection =
                DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();


        String sql = "select * from item";

        ResultSet result = statement.executeQuery(sql);
        List<Item> list = new ArrayList<>();
        while (result.next()) {

            String name = result.getString("name");
            int baseprice = result.getInt("baseprice");
            int id = result.getInt("id");
            StockItem item = new StockItem(id, baseprice, name);
            list.add(item);

        }

        return list;

    }
}
