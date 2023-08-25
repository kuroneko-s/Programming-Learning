package structor.composite;

import java.util.ArrayList;
import java.util.List;

public class Bag implements ItemComponent {
    private List<ItemComponent> itemList = new ArrayList<>();

    public Bag() {
    }

    public void append(ItemComponent item) {
        itemList.add(item);
    }

    public List<ItemComponent> getItem() {
        return itemList;
    }

    @Override
    public int getPrice() {
        return itemList.stream().mapToInt(ItemComponent::getPrice).sum();
    }
}
