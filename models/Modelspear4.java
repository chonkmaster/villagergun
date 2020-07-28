// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports

public static class Modelspear4 extends EntityModel {
	private final ModelRenderer spearthingy;

	public Modelspear4() {
		textureWidth = 32;
		textureHeight = 32;

		spearthingy = new ModelRenderer(this);
		spearthingy.setRotationPoint(0.0F, 1.0F, 0.0F);
		setRotationAngle(spearthingy, 3.1416F, 0.0F, 0.0F);
		spearthingy.cubeList.add(new ModelBox(spearthingy, 16, 0, -1.0F, -22.0F, -1.0F, 1, 3, 3, 0.0F, false));
		spearthingy.cubeList.add(new ModelBox(spearthingy, 16, 0, -1.0F, -23.0F, 0.0F, 1, 1, 1, 0.0F, false));
		spearthingy.cubeList.add(new ModelBox(spearthingy, 16, 0, 0.0F, -22.0F, 0.0F, 1, 3, 1, 0.0F, false));
		spearthingy.cubeList.add(new ModelBox(spearthingy, 16, 0, -2.0F, -22.0F, 0.0F, 1, 3, 1, 0.0F, false));
		spearthingy.cubeList.add(new ModelBox(spearthingy, 10, 0, -1.0F, -19.0F, 0.0F, 1, 31, 1, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		spearthingy.render(f5);
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