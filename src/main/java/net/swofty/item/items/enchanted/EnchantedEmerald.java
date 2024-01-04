package net.swofty.item.items.enchanted;


import net.swofty.item.ItemType;
import net.swofty.item.impl.Enchanted;
import net.swofty.item.impl.SkyBlockRecipe;

public class EnchantedEmerald implements Enchanted {
      @Override
      public ItemType getCraftingMaterial() {
            return ItemType.EMERALD;
      }

      @Override
      public SkyBlockRecipe.RecipeType getRecipeType() {
            return SkyBlockRecipe.RecipeType.MINING;
      }
}