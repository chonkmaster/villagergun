package net.mcreator.villagergun.procedures;

import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.item.ItemEntity;

import net.mcreator.villagergun.item.SpearItem;
import net.mcreator.villagergun.VillagergunModElements;

@VillagergunModElements.ModElement.Tag
public class SPEARBULLETHITSBLOCKProcedure extends VillagergunModElements.ModElement {
	public SPEARBULLETHITSBLOCKProcedure(VillagergunModElements instance) {
		super(instance, 20);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure SPEARBULLETHITSBLOCK!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure SPEARBULLETHITSBLOCK!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure SPEARBULLETHITSBLOCK!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure SPEARBULLETHITSBLOCK!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if ((Math.random() >= 0)) {
			if (!world.isRemote) {
				ItemEntity entityToSpawn = new ItemEntity(world, x, y, z, new ItemStack(SpearItem.block, (int) (1)));
				entityToSpawn.setPickupDelay(10);
				world.addEntity(entityToSpawn);
			}
		}
	}
}
