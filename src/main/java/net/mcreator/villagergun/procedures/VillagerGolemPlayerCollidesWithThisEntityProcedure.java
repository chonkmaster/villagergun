package net.mcreator.villagergun.procedures;

import net.minecraft.util.Hand;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.villagergun.VillagergunModElements;

@VillagergunModElements.ModElement.Tag
public class VillagerGolemPlayerCollidesWithThisEntityProcedure extends VillagergunModElements.ModElement {
	public VillagerGolemPlayerCollidesWithThisEntityProcedure(VillagergunModElements instance) {
		super(instance, 24);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure VillagerGolemPlayerCollidesWithThisEntity!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof LivingEntity) {
			((LivingEntity) entity).swingArm(Hand.MAIN_HAND);
		}
		for (int index0 = 0; index0 < (int) (6); index0++) {
			{
				Entity _ent = entity;
				if (!_ent.world.isRemote && _ent.world.getServer() != null) {
					_ent.world.getServer().getCommandManager().handleCommand(_ent.getCommandSource().withFeedbackDisabled().withPermissionLevel(4),
							"/execute at @e[type=!villagergun:villager_golem,distance=..3] run tp @e[type=!villagergun:villager_golem,distance=..3] ~ ~1.5 ~");
				}
			}
		}
	}
}
