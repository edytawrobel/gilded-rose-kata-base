package org.example.test;

import org.example.src.Rose;
import org.example.src.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoseTest {

    @Test
    public void regularItemsQualityShouldBeDegradedAfterOneDay() {
        Item item = new Item("+5 Dexterity Vest", 10, 20);
        Item[] items = new Item[]{item};
        Rose app = new Rose(items);

        app.updateQuality();

        assertEquals(9, items[0].sellIn);
        assertEquals(19, items[0].quality);
    }

    @Test
    public void regularItemsQualityShouldBeDegradedTwiceAsFastWhenSellInDateHasPassed() {
        Item item = new Item("+5 Dexterity Vest", 0, 20);
        Item[] items = new Item[]{item};
        Rose app = new Rose(items);

        app.updateQuality();

        assertEquals(-1, items[0].sellIn);
        assertEquals(18, items[0].quality);
    }

    @Test
    public void qualityOfAnItemShouldNeverBeNegative() {
        Item item = new Item("+5 Dexterity Vest", 0, 0);
        Item[] items = new Item[]{item};
        Rose app = new Rose(items);

        app.updateQuality();

        assertEquals(-1, items[0].sellIn);
        assertEquals(0, items[0].quality);
    }

    @Test
    public void agedBriesQualityShouldIncreaseAfterOneDay() {
        Item item = new Item("Aged Brie", 2, 10);
        Item[] items = new Item[]{item};

        Rose app = new Rose(items);

        app.updateQuality();

        assertEquals(1, items[0].sellIn);
        assertEquals(11, items[0].quality);
    }

    @Test
    public void agedBriesQualityShouldIncreaseBy2IfItHasExpired() {
        Item item = new Item("Aged Brie", 0, 10);
        Item[] items = new Item[]{item};

        Rose app = new Rose(items);

        app.updateQuality();

        assertEquals(-1, items[0].sellIn);
        assertEquals(12, items[0].quality);
    }

    @Test
    public void qualityOfAnItemShouldNotIncreaseMoreThan50() {
        Item item = new Item("Aged Brie", 2, 50);
        Item[] items = new Item[]{item};
        Rose app = new Rose(items);

        app.updateQuality();

        assertEquals(1, items[0].sellIn);
        assertEquals(50, items[0].quality);
    }

    @Test
    public void sulfurasShouldNeverDecreaseItsQuality() {
        Item item = new Item("Sulfuras, Hand of Ragnaros", 0, 80);
        Item[] items = new Item[]{item};

        Rose app = new Rose(items);

        app.updateQuality();

        assertEquals(0, items[0].sellIn);
        assertEquals(80, items[0].quality);
    }

    @Test
    public void backstagePassesShouldIncreasItsQualityBy1WhenMoreThan10daysBeforeSellInDate() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 11, 20);
        Item[] items = new Item[]{item};
        Rose app = new Rose(items);

        app.updateQuality();

        assertEquals(10, items[0].sellIn);
        assertEquals(21, items[0].quality);
    }

    @Test
    public void backstagePassesShouldIncreasItsQualityBy2When10daysBeforeSellInDate() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20);
        Item[] items = new Item[]{item};
        Rose app = new Rose(items);

        app.updateQuality();

        assertEquals(9, items[0].sellIn);
        assertEquals(22, items[0].quality);
    }

    @Test
    public void backstagePassesShouldIncreaseItsQualityBy2WhenLessThan10daysBeforeSellInDate() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 9, 20);
        Item[] items = new Item[]{item};
        Rose app = new Rose(items);

        app.updateQuality();

        assertEquals(8, items[0].sellIn);
        assertEquals(22, items[0].quality);
    }

    @Test
    public void backstagePassesShouldIncreasesItsQualityBy3When5daysBeforeSellInDate() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20);
        Item[] items = new Item[]{item};
        Rose app = new Rose(items);

        app.updateQuality();

        assertEquals(4, items[0].sellIn);
        assertEquals(23, items[0].quality);
    }

    @Test
    public void backstagePassesShouldDropTheirQualityToZeroWhenArrivedSellInDate() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20);
        Item[] items = new Item[]{item};
        Rose app = new Rose(items);

        app.updateQuality();

        assertEquals(-1, items[0].sellIn);
        assertEquals(0, items[0].quality);
    }
}
