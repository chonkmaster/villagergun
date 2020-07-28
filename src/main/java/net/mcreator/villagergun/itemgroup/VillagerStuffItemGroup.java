
package net.mcreator.villagergun.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.mcreator.villagergun.block.BlockofVillagerBlock;
import net.mcreator.villagergun.VillagergunModElements;

@VillagergunModElements.ModElement.Tag
public class VillagerStuffItemGroup extends VillagergunModElements.ModElement {
	public VillagerStuffItemGroup(VillagergunModElements instance) {
		super(instance, 40);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabvillager_stuff") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(BlockofVillagerBlock.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
