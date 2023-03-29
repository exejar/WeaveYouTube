package club.maxstats.weaveyoutube.event;

import club.maxstats.weave.loader.api.event.Event;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;

public class MyCustomEvent extends Event {
    public final RendererLivingEntity<EntityLivingBase> renderer;
    public final EntityLivingBase entity;
    public final double x, y, z;
    public final float partialTicks;

    public MyCustomEvent(RendererLivingEntity<EntityLivingBase> renderer, EntityLivingBase entity, double x, double y, double z, float partialTicks) {
        this.renderer = renderer;
        this.entity = entity;
        this.x = x;
        this.y = y;
        this.z = z;
        this.partialTicks = partialTicks;
    }
}
