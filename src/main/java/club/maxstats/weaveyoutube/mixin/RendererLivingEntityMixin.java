package club.maxstats.weaveyoutube.mixin;

import club.maxstats.weave.loader.api.event.EventBus;
import club.maxstats.weaveyoutube.event.MyCustomEvent;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RendererLivingEntity.class)
public class RendererLivingEntityMixin {
    @Inject(method = "doRender(Lnet/minecraft/entity/EntityLivingBase;DDDFF)V", at = @At(value = "RETURN", shift = At.Shift.BEFORE))
    public void doRender(EntityLivingBase entity, double x, double y, double z, float entityYaw, float partialTicks, CallbackInfo ci) {
        EventBus.callEvent(new MyCustomEvent((RendererLivingEntity<EntityLivingBase>)(Object)this, entity, x, y, z, partialTicks));
    }
}
