package structor.composite;

import javax.swing.*;

public class Client {
    public static void main(String[] args) {
        Item item1 = new Item("도란", 450);
        Item item2 = new Item("물약", 50);

        Bag bag = new Bag();
        bag.append(item1);
        bag.append(item2);

        // JFrame
    }

    private void printPrice(ItemComponent component) {
        System.out.println(component.getPrice());
    }
}
