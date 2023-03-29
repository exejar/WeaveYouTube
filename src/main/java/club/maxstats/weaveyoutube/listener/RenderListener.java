package club.maxstats.weaveyoutube.listener;

import club.maxstats.weave.loader.api.event.RenderLivingEvent;
import club.maxstats.weave.loader.api.event.SubscribeEvent;
import club.maxstats.weaveyoutube.event.MyCustomEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.AxisAlignedBB;

import static org.lwjgl.opengl.GL11.*;

public class RenderListener {
    public static final int NPC_UUID_VERSION = 2;
    public boolean renderESP = true;

//    @SubscribeEvent
//    public void onWeaveEvent(RenderLivingEvent.Post event) {
//        renderESP(event.getEntity(), event.getPartialTicks());
//    }

    @SubscribeEvent
    public void onMyEvent(MyCustomEvent event) {
        renderESP(event.entity, event.partialTicks);
    }

    private void renderESP(Entity entity, float partialTicks) {
        if (entity.getUniqueID().version() != NPC_UUID_VERSION && this.renderESP) {
            glDisable(GL_DEPTH_TEST);
            GlStateManager.disableAlpha();
            GlStateManager.disableTexture2D();
            GlStateManager.disableLighting();

            render2dBox((EntityLiving)entity, Minecraft.getMinecraft().getRenderManager(), partialTicks);

            GlStateManager.color(1.0F, 1.0F, 1.0F);
            GlStateManager.enableLighting();
            GlStateManager.enableTexture2D();
            GlStateManager.enableAlpha();
            glEnable(GL_DEPTH_TEST);
        }
    }
    private void render2dBox(EntityLiving entity, RenderManager renderManager, float partialTicks) {
        glPushMatrix();

        AxisAlignedBB aabb = entity.getEntityBoundingBox();
        double entityWidth = aabb.maxX - aabb.minX;
        double entityHeight = aabb.maxY - aabb.minY;
        double centerX = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * partialTicks - renderManager.viewerPosX;
        double centerY = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * partialTicks - renderManager.viewerPosY + (entityHeight / 2);
        double centerZ = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * partialTicks - renderManager.viewerPosZ;

        glTranslated(centerX, centerY, centerZ);
        glRotatef(-renderManager.playerViewY, 0F, 1F, 0F);
        glRotatef(renderManager.playerViewX, 1F, 0F, 0F);

        float size = (float)Math.max(entityWidth, entityHeight) + 0.5F;
        float sizeFromCenter = size / 2.0F;

        GlStateManager.color(1.0F, 0.0F, 0.0F);
        glBegin(GL_LINE_LOOP);
        glVertex2f(sizeFromCenter, sizeFromCenter);
        glVertex2f(sizeFromCenter, -sizeFromCenter);
        glVertex2f(-sizeFromCenter, -sizeFromCenter);
        glVertex2f(-sizeFromCenter, sizeFromCenter);
        glEnd();

        float healthSize = (entity.getHealth() / entity.getMaxHealth()) * size;
        GlStateManager.color(1.0F - healthSize, healthSize, 0.0F);
        glBegin(GL_QUADS);
        glVertex2f(size * 0.65F, -sizeFromCenter + healthSize);
        glVertex2f(size * 0.65F, -sizeFromCenter);
        glVertex2f(size * 0.6F, -sizeFromCenter);
        glVertex2f(size * 0.6F, -sizeFromCenter + healthSize);
        glEnd();

        glPopMatrix();
    }
}
