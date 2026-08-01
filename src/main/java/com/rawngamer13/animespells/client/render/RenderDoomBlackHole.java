package com.rawngamer13.animespells.client.render;

import com.rawngamer13.animespells.spells.DoomBlackHole;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderDoomBlackHole extends Render<DoomBlackHole> {
    public RenderDoomBlackHole(RenderManager renderManager) {
        super(renderManager);
    }

    @Override
    public void doRender(DoomBlackHole entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x, (float) y, (float) z);

        // Dibujar el agujero negro (esfera negra)
        drawBlackHole();

        // Dibujar los bordes rojos
        drawRedBorders();

        GL11.glPopMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    private void drawBlackHole() {
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glColor3f(0, 0, 0); // Negro
        renderSphere(0, 0, 0, 1.5f);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }

    private void drawRedBorders() {
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glColor3f(1, 0.2f, 0); // Rojo carmesí
        GL11.glLineWidth(2.0f);
        renderSphere(0, 0, 0, 2.0f);
        GL11.glLineWidth(1.0f);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }

    private void renderSphere(float x, float y, float z, float radius) {
        net.minecraft.client.renderer.Tessellator tessellator = net.minecraft.client.renderer.Tessellator.getInstance();
        net.minecraft.client.renderer.BufferBuilder bufferbuilder = tessellator.getBuffer();
        
        int slices = 32;
        int stacks = 32;

        for (int i = 0; i < stacks; i++) {
            float lat0 = (float) Math.PI * (-0.5f + (float) i / stacks);
            float lat1 = (float) Math.PI * (-0.5f + (float) (i + 1) / stacks);

            float z0 = (float) Math.sin(lat0) * radius;
            float z1 = (float) Math.sin(lat1) * radius;

            float r0 = (float) Math.cos(lat0) * radius;
            float r1 = (float) Math.cos(lat1) * radius;

            for (int j = 0; j <= slices; j++) {
                float lng = 2 * (float) Math.PI * (float) j / slices;
                float sinLng = (float) Math.sin(lng);
                float cosLng = (float) Math.cos(lng);

                GL11.glVertex3f(r1 * cosLng, z1, r1 * sinLng);
                GL11.glVertex3f(r0 * cosLng, z0, r0 * sinLng);
            }
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(DoomBlackHole entity) {
        return null;
    }
}
