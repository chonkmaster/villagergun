
package net.mcreator.villagergun.block;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.villagergun.itemgroup.VillagerStuffItemGroup;
import net.mcreator.villagergun.VillagergunModElements;

import java.util.List;
import java.util.Collections;

@VillagergunModElements.ModElement.Tag
public class VillagerLeavesBlock extends VillagergunModElements.ModElement {
	@ObjectHolder("villagergun:villager_leaves")
	public static final Block block = null;
	public VillagerLeavesBlock(VillagergunModElements instance) {
		super(instance, 35);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items
				.add(() -> new BlockItem(block, new Item.Properties().group(VillagerStuffItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}
	public static class CustomBlock extends Block {
		public CustomBlock() {
			super(Block.Properties.create(Material.LEAVES).sound(SoundType.STEM).hardnessAndResistance(1f, 10f).lightValue(0));
			setRegistryName("villager_leaves");
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 1));
		}
	}
}
