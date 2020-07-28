// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports

public static class Modelvillagermonk extends EntityModel {
	private final ModelRenderer everything;
	private final ModelRenderer legs;
	private final ModelRenderer bone6;
	private final ModelRenderer legs2;
	private final ModelRenderer legs3;
	private final ModelRenderer bone5;
	private final ModelRenderer bone4;
	private final ModelRenderer bone3;
	private final ModelRenderer bone2;
	private final ModelRenderer bone;

	public Modelvillagermonk() {
		textureWidth = 128;
		textureHeight = 128;

		everything = new ModelRenderer(this);
		everything.setRotationPoint(0.0F, 24.0F, 0.0F);
		everything.cubeList.add(new ModelBox(everything, 0, 0, -5.5F, -34.0F, -3.0F, 10, 12, 8, 0.0F, false));
		everything.cubeList.add(new ModelBox(everything, 55, 56, -3.0F, -23.0F, 2.0F, 5, 7, 3, 0.0F, false));
		everything.cubeList.add(new ModelBox(everything, 16, 41, -2.0F, -26.0F, -5.0F, 3, 6, 2, 0.0F, false));
		everything.cubeList.add(new ModelBox(everything, 0, 20, -5.0F, -21.0F, 1.0F, 9, 15, 6, 0.0F, false));

		legs = new ModelRenderer(this);
		legs.setRotationPoint(2.0F, 0.0F, -5.0F);
		everything.addChild(legs);
		setRotationAngle(legs, -1.4835F, -1.0472F, 0.2618F);
		legs.cubeList.add(new ModelBox(legs, 26, 38, -3.7339F, -13.0426F, -2.6402F, 4, 15, 4, 0.0F, false));

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(1.0F, -16.0F, -11.0F);
		everything.addChild(bone6);
		setRotationAngle(bone6, 0.3491F, 0.0F, 0.0F);
		bone6.cubeList.add(new ModelBox(bone6, 28, 57, -3.0F, -7.0F, 0.0F, 3, 7, 4, 0.0F, false));

		legs2 = new ModelRenderer(this);
		legs2.setRotationPoint(-9.0F, -9.0F, -13.0F);
		everything.addChild(legs2);
		setRotationAngle(legs2, -1.4835F, -2.0071F, 0.1745F);
		legs2.cubeList.add(new ModelBox(legs2, 42, 42, -0.8651F, 8.5278F, -12.9032F, 4, 13, 4, 0.0F, false));

		legs3 = new ModelRenderer(this);
		legs3.setRotationPoint(0.0F, -11.0F, -1.0F);
		everything.addChild(legs3);
		setRotationAngle(legs3, -1.3963F, 2.0071F, -0.0873F);
		legs3.cubeList.add(new ModelBox(legs3, 0, 41, 4.9985F, -3.5428F, -8.4068F, 4, 13, 4, 0.0F, false));

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(8.0F, 0.0F, -8.0F);
		everything.addChild(bone5);
		setRotationAngle(bone5, 0.0873F, 0.6981F, 0.4363F);
		bone5.cubeList.add(new ModelBox(bone5, 30, 30, -17.5417F, -16.8195F, 0.4405F, 12, 4, 4, 0.0F, false));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(10.0F, -4.0F, 3.0F);
		everything.addChild(bone4);
		setRotationAngle(bone4, 0.0F, -0.6109F, -0.3491F);
		bone4.cubeList.add(new ModelBox(bone4, 28, 0, -17.8528F, -20.5774F, 3.4602F, 13, 4, 4, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(10.0F, -5.0F, 0.0F);
		everything.addChild(bone3);
		setRotationAngle(bone3, -1.2217F, -0.8727F, -0.0873F);
		bone3.cubeList.add(new ModelBox(bone3, 52, 8, -4.0F, -11.0F, 0.0F, 4, 11, 4, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(-8.0F, -5.0F, -3.0F);
		everything.addChild(bone2);
		setRotationAngle(bone2, -1.2217F, 0.8727F, 0.0873F);
		bone2.cubeList.add(new ModelBox(bone2, 12, 54, -4.0F, -11.0F, 0.0F, 4, 11, 4, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(6.0F, -5.5821F, -4.1248F);
		everything.addChild(bone);
		setRotationAngle(bone, -1.5708F, 1.0472F, -0.2618F);
		bone.cubeList.add(new ModelBox(bone, 36, 8, -4.1176F, -4.3322F, 0.0611F, 4, 15, 4, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		everything.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
	}
}