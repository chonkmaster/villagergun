
package net.mcreator.villagergun.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.FollowMobGoal;
import net.minecraft.entity.ai.goal.BreakDoorGoal;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.block.BlockState;

import net.mcreator.villagergun.itemgroup.VillagerStuffItemGroup;
import net.mcreator.villagergun.item.BookItem;
import net.mcreator.villagergun.VillagergunModElements;

import java.util.Random;

@VillagergunModElements.ModElement.Tag
public class VillagerMonkEntity extends VillagergunModElements.ModElement {
	public static EntityType entity = null;
	public VillagerMonkEntity(VillagergunModElements instance) {
		super(instance, 36);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.6f, 1.8f)).build("villager_monk")
						.setRegistryName("villager_monk");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -4029876, -2658048, new Item.Properties().group(VillagerStuffItemGroup.tab))
				.setRegistryName("villager_monk"));
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(CustomEntity.class, renderManager -> {
			return new MobRenderer(renderManager, new Modelvillagermonk(), 0.5f) {
				@Override
				protected ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("villagergun:textures/villagermonk1.png");
				}
			};
		});
	}
	public static class CustomEntity extends BlazeEntity implements IRangedAttackMob {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 0;
			setNoAI(false);
			this.moveController = new FlyingMovementController(this);
			this.navigator = new FlyingPathNavigator(this, this.world);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.goalSelector.addGoal(1, new RandomWalkingGoal(this, 0.8, 20) {
				@Override
				protected Vec3d getPosition() {
					Random random = CustomEntity.this.getRNG();
					double dir_x = CustomEntity.this.posX + ((random.nextFloat() * 2 - 1) * 16);
					double dir_y = CustomEntity.this.posY + ((random.nextFloat() * 2 - 1) * 16);
					double dir_z = CustomEntity.this.posZ + ((random.nextFloat() * 2 - 1) * 16);
					return new Vec3d(dir_x, dir_y, dir_z);
				}
			});
			this.targetSelector.addGoal(2, new HurtByTargetGoal(this).setCallsForHelp(this.getClass()));
			this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.2, false));
			this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, PlayerEntity.class, true, true));
			this.goalSelector.addGoal(5, new FollowMobGoal(this, (float) 1, 10, 5));
			this.goalSelector.addGoal(6, new BreakDoorGoal(this, e -> true));
			this.goalSelector.addGoal(7, new RandomWalkingGoal(this, 0.8));
			this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(9, new SwimGoal(this));
			this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.25, 20, 10));
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
		public void fall(float l, float d) {
		}

		@Override
		public boolean attackEntityFrom(DamageSource source, float amount) {
			if (source == DamageSource.FALL)
				return false;
			return super.attackEntityFrom(source, amount);
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50);
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10);
			if (this.getAttribute(SharedMonsterAttributes.FLYING_SPEED) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
			this.getAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(0.3);
		}

		public void attackEntityWithRangedAttack(LivingEntity target, float flval) {
			BookItem.shoot(this, target);
		}

		@Override
		protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
		}

		@Override
		public void setNoGravity(boolean ignored) {
			super.setNoGravity(true);
		}

		public void livingTick() {
			super.livingTick();
			this.setNoGravity(true);
		}
	}

	// Made with Blockbench 3.5.4
	// Exported for Minecraft version 1.14
	// Paste this class into your mod and generate all required imports
	public static class Modelvillagermonk extends EntityModel {
		private final RendererModel everything;
		private final RendererModel legs;
		private final RendererModel bone6;
		private final RendererModel legs2;
		private final RendererModel legs3;
		private final RendererModel bone5;
		private final RendererModel bone4;
		private final RendererModel bone3;
		private final RendererModel bone2;
		private final RendererModel bone;
		public Modelvillagermonk() {
			textureWidth = 128;
			textureHeight = 128;
			everything = new RendererModel(this);
			everything.setRotationPoint(0.0F, 24.0F, 0.0F);
			everything.cubeList.add(new ModelBox(everything, 0, 0, -5.5F, -34.0F, -3.0F, 10, 12, 8, 0.0F, false));
			everything.cubeList.add(new ModelBox(everything, 55, 56, -3.0F, -23.0F, 2.0F, 5, 7, 3, 0.0F, false));
			everything.cubeList.add(new ModelBox(everything, 16, 41, -2.0F, -26.0F, -5.0F, 3, 6, 2, 0.0F, false));
			everything.cubeList.add(new ModelBox(everything, 0, 20, -5.0F, -21.0F, 1.0F, 9, 15, 6, 0.0F, false));
			legs = new RendererModel(this);
			legs.setRotationPoint(2.0F, 0.0F, -5.0F);
			everything.addChild(legs);
			setRotationAngle(legs, -1.4835F, -1.0472F, 0.2618F);
			legs.cubeList.add(new ModelBox(legs, 26, 38, -3.7339F, -13.0426F, -2.6402F, 4, 15, 4, 0.0F, false));
			bone6 = new RendererModel(this);
			bone6.setRotationPoint(1.0F, -16.0F, -11.0F);
			everything.addChild(bone6);
			setRotationAngle(bone6, 0.3491F, 0.0F, 0.0F);
			bone6.cubeList.add(new ModelBox(bone6, 28, 57, -3.0F, -7.0F, 0.0F, 3, 7, 4, 0.0F, false));
			legs2 = new RendererModel(this);
			legs2.setRotationPoint(-9.0F, -9.0F, -13.0F);
			everything.addChild(legs2);
			setRotationAngle(legs2, -1.4835F, -2.0071F, 0.1745F);
			legs2.cubeList.add(new ModelBox(legs2, 42, 42, -0.8651F, 8.5278F, -12.9032F, 4, 13, 4, 0.0F, false));
			legs3 = new RendererModel(this);
			legs3.setRotationPoint(0.0F, -11.0F, -1.0F);
			everything.addChild(legs3);
			setRotationAngle(legs3, -1.3963F, 2.0071F, -0.0873F);
			legs3.cubeList.add(new ModelBox(legs3, 0, 41, 4.9985F, -3.5428F, -8.4068F, 4, 13, 4, 0.0F, false));
			bone5 = new RendererModel(this);
			bone5.setRotationPoint(8.0F, 0.0F, -8.0F);
			everything.addChild(bone5);
			setRotationAngle(bone5, 0.0873F, 0.6981F, 0.4363F);
			bone5.cubeList.add(new ModelBox(bone5, 30, 30, -17.5417F, -16.8195F, 0.4405F, 12, 4, 4, 0.0F, false));
			bone4 = new RendererModel(this);
			bone4.setRotationPoint(10.0F, -4.0F, 3.0F);
			everything.addChild(bone4);
			setRotationAngle(bone4, 0.0F, -0.6109F, -0.3491F);
			bone4.cubeList.add(new ModelBox(bone4, 28, 0, -17.8528F, -20.5774F, 3.4602F, 13, 4, 4, 0.0F, false));
			bone3 = new RendererModel(this);
			bone3.setRotationPoint(10.0F, -5.0F, 0.0F);
			everything.addChild(bone3);
			setRotationAngle(bone3, -1.2217F, -0.8727F, -0.0873F);
			bone3.cubeList.add(new ModelBox(bone3, 52, 8, -4.0F, -11.0F, 0.0F, 4, 11, 4, 0.0F, false));
			bone2 = new RendererModel(this);
			bone2.setRotationPoint(-8.0F, -5.0F, -3.0F);
			everything.addChild(bone2);
			setRotationAngle(bone2, -1.2217F, 0.8727F, 0.0873F);
			bone2.cubeList.add(new ModelBox(bone2, 12, 54, -4.0F, -11.0F, 0.0F, 4, 11, 4, 0.0F, false));
			bone = new RendererModel(this);
			bone.setRotationPoint(6.0F, -5.5821F, -4.1248F);
			everything.addChild(bone);
			setRotationAngle(bone, -1.5708F, 1.0472F, -0.2618F);
			bone.cubeList.add(new ModelBox(bone, 36, 8, -4.1176F, -4.3322F, 0.0611F, 4, 15, 4, 0.0F, false));
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			everything.render(f5);
		}

		public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4, float f5) {
			super.setRotationAngles(e, f, f1, f2, f3, f4, f5);
		}
	}
}
