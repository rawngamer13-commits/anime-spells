package com.rawngamer13.animespells.event;

import com.rawngamer13.animespells.spells.DoomBlackHole;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.client.event.RenderSkyEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class SkyRenderHandler {
    
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onRenderSky(RenderSkyEvent.Pre event) {
        World world = Minecraft.getMinecraft().world;
        if (world == null) return;
        
        // Buscar agujeros negros Doom en el mundo
        boolean hasDoomActive = false;
        for (Entity entity : world.loadedEntityList) {
            if (entity instanceof DoomBlackHole) {
                hasDoomActive = true;
                break;
            }
        }
        
        if (hasDoomActive) {
            // Cambiar el cielo a rojo carmesí
            event.setCanceled(true);
            
            GL11.glDisable(GL11.GL_FOG);
            GL11.glMatrixMode(GL11.GL_PROJECTION);
            GL11.glPushMatrix();
            GL11.glLoadIdentity();
            
            // Dibujar cielo rojo
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            GL11.glPushMatrix();
            GL11.glLoadIdentity();
            
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glColor3f(0.8f, 0.1f, 0.1f); // Rojo carmesí
            
            // Dibujar una esfera de cielo
            drawFullSky();
            
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glPopMatrix();
            GL11.glMatrixMode(GL11.GL_PROJECTION);
            GL11.glPopMatrix();
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
        }
    }
    
    private static void drawFullSky() {
        // Dibujar un cubo de cielo rojo carmesí
        float size = 500f;
        GL11.glBegin(GL11.GL_QUADS);
        
        // Arriba
        GL11.glVertex3f(-size, size, -size);
        GL11.glVertex3f(size, size, -size);
        GL11.glVertex3f(size, size, size);
        GL11.glVertex3f(-size, size, size);
        
        // Abajo
        GL11.glVertex3f(-size, -size, -size);
        GL11.glVertex3f(-size, -size, size);
        GL11.glVertex3f(size, -size, size);
        GL11.glVertex3f(size, -size, -size);
        
        // Frente
        GL11.glVertex3f(-size, -size, size);
        GL11.glVertex3f(-size, size, size);
        GL11.glVertex3f(size, size, size);
        GL11.glVertex3f(size, -size, size);
        
        // Atrás
        GL11.glVertex3f(-size, -size, -size);
        GL11.glVertex3f(size, -size, -size);
        GL11.glVertex3f(size, size, -size);
        GL11.glVertex3f(-size, size, -size);
        
        // Derecha
        GL11.glVertex3f(size, -size, -size);
        GL11.glVertex3f(size, -size, size);
        GL11.glVertex3f(size, size, size);
        GL11.glVertex3f(size, size, -size);
        
        // Izquierda
        GL11.glVertex3f(-size, -size, -size);
        GL11.glVertex3f(-size, size, -size);
        GL11.glVertex3f(-size, size, size);
        GL11.glVertex3f(-size, -size, size);
        
        GL11.glEnd();
    }
}
