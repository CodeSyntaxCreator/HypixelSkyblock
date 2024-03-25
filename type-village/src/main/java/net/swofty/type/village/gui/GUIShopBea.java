package net.swofty.type.village.gui;

import net.minestom.server.item.Material;
import net.swofty.types.generic.gui.inventory.SkyBlockShopGUI;
import net.swofty.types.generic.item.ItemType;
import net.swofty.types.generic.item.Rarity;
import net.swofty.types.generic.item.SkyBlockItem;
import net.swofty.types.generic.shop.type.CoinShopPrice;
import net.swofty.types.generic.shop.type.CombinedShopPrice;
import net.swofty.types.generic.shop.type.ItemShopPrice;

import java.util.ArrayList;
import java.util.List;

public class GUIShopBea extends SkyBlockShopGUI {
    public GUIShopBea() {
        super("Pea", 1, DEFAULT);
    }

    @Override
    public void initializeShopItems() {
        attachBeePet(Rarity.COMMON, new CombinedShopPrice(List.of(
                new CoinShopPrice(4999),
                new ItemShopPrice(ItemType.COAL_BLOCK, 2),
                new ItemShopPrice(ItemType.GOLD_BLOCK, 2)
        )));
        attachBeePet(Rarity.RARE, new CombinedShopPrice(List.of(
                new CoinShopPrice(50000),
                new ItemShopPrice(ItemType.COAL_BLOCK, 128),
                new ItemShopPrice(ItemType.GOLD_BLOCK, 128)
        )));
        attachBeePet(Rarity.EPIC, new CombinedShopPrice(List.of(
                new CoinShopPrice(200000),
                new ItemShopPrice(ItemType.ENCHANTED_COAL_BLOCK, 1),
                new ItemShopPrice(ItemType.ENCHANTED_GOLD_BLOCK, 1)
        )));
        attachBeePet(Rarity.LEGENDARY, new CombinedShopPrice(List.of(
                new CoinShopPrice(650000),
                new ItemShopPrice(ItemType.ENCHANTED_COAL_BLOCK, 8),
                new ItemShopPrice(ItemType.ENCHANTED_GOLD_BLOCK, 8)
        )));
    }

    public void attachBeePet(Rarity rarity, CombinedShopPrice price) {
        ShopItem bee = ShopItem.Single(new SkyBlockItem(ItemType.BEE_PET),
                1,
                price,
                1);
        SkyBlockItem beeItem = new SkyBlockItem(ItemType.BEE_PET);
        beeItem.getAttributeHandler().getPetData().setLevel(100, rarity);

        ArrayList<String> lore = new ArrayList<>(beeItem.getLore());
        lore.add(" ");
        lore.add("§cThis is a preview of Lvl 100");
        lore.add("§cNew pets are lowest level!");

        bee.setDisplayLore(lore);
        attachItem(bee);
    }
}
