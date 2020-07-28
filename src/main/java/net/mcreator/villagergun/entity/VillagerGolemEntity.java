
package net.mcreator.villagergun.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.World;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.FollowMobGoal;
import net.minecraft.entity.ai.goal.BreakDoorGoal;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import net.mcreator.villagergun.procedures.VillagerGolemPlayerCollidesWithThisEntityProcedure;
import net.mcreator.villagergun.VillagergunModElements;

@VillagergunModElements.ModElement.Tag
public class VillagerGolemEntity extends VillagergunModElements.ModElement {
	public static EntityType entity = null;
	public VillagerGolemEntity(VillagergunModElements instance) {
		super(instance, 23);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(1f, 5f)).build("villager_golem")
						.setRegistryName("villager_golem");
		elements.entities.add(() -> entity);
		elements.items.add(
				() -> new SpawnEggItem(entity, -10079488, -13421773, new Item.Properties().group(ItemGroup.MISC)).setRegistryName("villager_golem"));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			biome.getSpawns(EntityClassification.MONSTER).add(new Biome.SpawnListEntry(entity, 20, 4, 4));
		}
		EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::func_223315_a);
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(CustomEntity.class, renderManager -> {
			return new MobRenderer(renderManager, new Modelcustom_model(), 2f) {
				@Override
				protected ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("villagergun:textures/bigvillager.png");
				}
			};
		});
	}
	public static class CustomEntity extends MonsterEntity {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 0;
			setNoAI(false);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
			this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2, false));
			this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, PlayerEntity.class, true, true));
			this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, GhostVillagerEntity.CustomEntity.class, true, true));
			this.goalSelector.addGoal(5, new FollowMobGoal(this, (float) 1, 10, 10));
			this.goalSelector.addGoal(6, new BreakDoorGoal(this, e -> true));
			this.goalSelector.addGoal(7, new RandomWalkingGoal(this, 1));
			this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(9, new SwimGoal(this));
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(""));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.death"));
		}

		@Override
		protected float getSoundVolume() {
			return 1.0F;
		}

		@Override
		public boolean attackEntityFrom(DamageSource source, float amount) {
			if (source.getImmediateSource() instanceof ArrowEntity)
				return false;
			if (source == DamageSource.FALL)
				return false;
			return super.attackEntityFrom(source, amount);
		}

		@Override
		public void onCollideWithPlayer(PlayerEntity sourceentity) {
			super.onCollideWithPlayer(sourceentity);
			Entity entity = this;
			int x = (int) this.posX;
			int y = (int) this.posY;
			int z = (int) this.posZ;
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("entity", entity);
				VillagerGolemPlayerCollidesWithThisEntityProcedure.executeProcedure($_dependencies);
			}
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50);
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(20);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(20);
		}
	}

	// Made with Blockbench 3.5.4
	// Exported for Minecraft version 1.14
	// Paste this class into your mod and generate all required imports
	public static class Modelcustom_model extends EntityModel {
		private final RendererModel body;
		private final RendererModel leftarm;
		private final RendererModel Head;
		private final RendererModel leftleg;
		private final RendererModel rightleg;
		private final RendererModel rightarm;
		public Modelcustom_model() {
			textureWidth = 256;
			textureHeight = 256;
			body = new RendererModel(this);
			body.setRotationPoint(20.0F, -16.0F, -10.0F);
			body.cubeList.add(new ModelBox(body, 0, 50, -34.0F, 0.0F, 4.0F, 28, 10, 12, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 0, 0, -40.0F, -30.0F, 0.0F, 40, 30, 20, 0.0F, false));
			leftarm = new RendererModel(this);
			leftarm.setRotationPoint(20.0F, -36.0F, 0.0F);
			leftarm.cubeList.add(new ModelBox(leftarm, 76, 125, 0.0F, -5.0F, -5.0F, 5, 10, 10, 0.0F, false));
			leftarm.cubeList.add(new ModelBox(leftarm, 0, 72, 3.0F, -6.0F, -6.0F, 8, 45, 12, 0.0F, false));
			Head = new RendererModel(this);
			Head.setRotationPoint(0.0F, -46.0F, -7.0F);
			Head.cubeList.add(new ModelBox(Head, 28, 117, -6.0F, -15.0F, -6.0F, 12, 15, 12, 0.0F, false));
			Head.cubeList.add(new ModelBox(Head, 0, 0, -2.0F, -5.0F, -9.0F, 4, 7, 3, 0.0F, false));
			leftleg = new RendererModel(this);
			leftleg.setRotationPoint(14.0F, -7.0F, 0.0F);
			leftleg.cubeList.add(new ModelBox(leftleg, 100, 0, -6.0F, 27.0F, -6.0F, 12, 4, 12, 0.0F, false));
			leftleg.cubeList.add(new ModelBox(leftleg, 108, 108, -5.0F, 0.0F, -5.0F, 10, 27, 10, 0.0F, false));
			rightleg = new RendererModel(this);
			rightleg.setRotationPoint(-14.0F, -6.0F, 1.0F);
			rightleg.cubeList.add(new ModelBox(rightleg, 108, 87, -6.0F, 26.0F, -7.0F, 12, 4, 12, 0.0F, false));
			rightleg.cubeList.add(new ModelBox(rightleg, 108, 50, -5.0F, -1.0F, -6.0F, 10, 27, 10, 0.0F, false));
			rightarm = new RendererModel(this);
			rightarm.setRotationPoint(-19.0F, -36.0F, 0.0F);
			rightarm.cubeList.add(new ModelBox(rightarm, 120, 16, -6.0F, -5.0F, -5.0F, 5, 10, 10, 0.0F, false));
			rightarm.cubeList.add(new ModelBox(rightarm, 68, 68, -12.0F, -6.0F, -6.0F, 8, 45, 12, 0.0F, false));
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			body.render(f5);
			leftarm.render(f5);
			Head.render(f5);
			leftleg.render(f5);
			rightleg.render(f5);
			rightarm.render(f5);
		}

		public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4, float f5) {
			super.setRotationAngles(e, f, f1, f2, f3, f4, f5);
			this.Head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.Head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.rightleg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.rightarm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.leftleg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.leftarm.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		}
	}
}
