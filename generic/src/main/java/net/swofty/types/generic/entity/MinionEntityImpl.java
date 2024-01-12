package net.swofty.types.generic.entity;

import lombok.Getter;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.LivingEntity;
import net.minestom.server.entity.metadata.other.ArmorStandMeta;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.item.metadata.LeatherArmorMeta;
import net.swofty.types.generic.gui.inventory.ItemStackCreator;
import net.swofty.types.generic.minion.IslandMinionData;
import net.swofty.types.generic.minion.SkyBlockMinion;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MinionEntityImpl extends LivingEntity {
    @Getter
    private static final List<MinionEntityImpl> activeMinions = new ArrayList<>();

    private final IslandMinionData.IslandMinion islandMinion;
    private final SkyBlockMinion minion;

    public MinionEntityImpl(IslandMinionData.IslandMinion islandMinion, SkyBlockMinion minion) {
        super(EntityType.ARMOR_STAND);

        this.islandMinion = islandMinion;
        this.minion = minion;

        setAutoViewable(true);
        setAutoViewEntities(true);

        ArmorStandMeta meta = (ArmorStandMeta) this.entityMeta;
        meta.setSmall(true);
        meta.setHasNoBasePlate(true);
        meta.setHasNoGravity(true);

        setInvisible(false);

        setBoots(ItemStack.builder(Material.LEATHER_BOOTS).meta(bootMeta -> {
            LeatherArmorMeta.Builder leatherMeta = new LeatherArmorMeta.Builder(bootMeta.tagHandler());
            leatherMeta.color(minion.getBootColour());
        }).build());
        setLeggings(ItemStack.builder(Material.LEATHER_LEGGINGS).meta(leggingsMeta -> {
            LeatherArmorMeta.Builder leatherMeta = new LeatherArmorMeta.Builder(leggingsMeta.tagHandler());
            leatherMeta.color(minion.getLeggingsColour());
        }).build());
        setChestplate(ItemStack.builder(Material.LEATHER_CHESTPLATE).meta(chestplateMeta -> {
            LeatherArmorMeta.Builder leatherMeta = new LeatherArmorMeta.Builder(chestplateMeta.tagHandler());
            leatherMeta.color(minion.getChestplateColour());
        }).build());

        setHelmet(ItemStackCreator.getStackHead(minion.getTexture()).build());

        setItemInMainHand(ItemStack.builder(minion.getHeldItem()).build());
    }

    @Override
    public void spawn() {
        activeMinions.add(this);
    }

    @Override
    public void remove() {
        super.remove();
        activeMinions.remove(this);
    }
}
