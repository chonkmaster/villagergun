// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports

public static class Modelcustom_model extends EntityModel {
	private final ModelRenderer body;
	private final ModelRenderer leftarm;
	private final ModelRenderer Head;
	private final ModelRenderer leftleg;
	private final ModelRenderer rightleg;
	private final ModelRenderer rightarm;

	public Modelcustom_model() {
		textureWidth = 256;
		textureHeight = 256;

		body = new ModelRenderer(this);
		body.setRotationPoint(20.0F, -16.0F, -10.0F);
		body.cubeList.add(new ModelBox(body, 0, 50, -34.0F, 0.0F, 4.0F, 28, 10, 12, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 0, -40.0F, -30.0F, 0.0F, 40, 30, 20, 0.0F, false));

		leftarm = new ModelRenderer(this);
		leftarm.setRotationPoint(20.0F, -36.0F, 0.0F);
		leftarm.cubeList.add(new ModelBox(leftarm, 76, 125, 0.0F, -5.0F, -5.0F, 5, 10, 10, 0.0F, false));
		leftarm.cubeList.add(new ModelBox(leftarm, 0, 72, 3.0F, -6.0F, -6.0F, 8, 45, 12, 0.0F, false));

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, -46.0F, -7.0F);
		Head.cubeList.add(new ModelBox(Head, 28, 117, -6.0F, -15.0F, -6.0F, 12, 15, 12, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 0, 0, -2.0F, -5.0F, -9.0F, 4, 7, 3, 0.0F, false));

		leftleg = new ModelRenderer(this);
		leftleg.setRotationPoint(14.0F, -7.0F, 0.0F);
		leftleg.cubeList.add(new ModelBox(leftleg, 100, 0, -6.0F, 27.0F, -6.0F, 12, 4, 12, 0.0F, false));
		leftleg.cubeList.add(new ModelBox(leftleg, 108, 108, -5.0F, 0.0F, -5.0F, 10, 27, 10, 0.0F, false));

		rightleg = new ModelRenderer(this);
		rightleg.setRotationPoint(-14.0F, -6.0F, 1.0F);
		rightleg.cubeList.add(new ModelBox(rightleg, 108, 87, -6.0F, 26.0F, -7.0F, 12, 4, 12, 0.0F, false));
		rightleg.cubeList.add(new ModelBox(rightleg, 108, 50, -5.0F, -1.0F, -6.0F, 10, 27, 10, 0.0F, false));

		rightarm = new ModelRenderer(this);
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

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.Head.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.Head.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.rightleg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.rightarm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		this.leftleg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.leftarm.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
	}
}