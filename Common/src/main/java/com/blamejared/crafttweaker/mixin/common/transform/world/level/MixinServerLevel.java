package com.blamejared.crafttweaker.mixin.common.transform.world.level;

import com.blamejared.crafttweaker.api.level.CraftTweakerSavedData;
import com.blamejared.crafttweaker.api.level.CraftTweakerSavedDataHolder;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.progress.ChunkProgressListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.storage.DimensionDataStorage;
import net.minecraft.world.level.storage.LevelStorageSource;
import net.minecraft.world.level.storage.ServerLevelData;
import net.minecraft.world.level.storage.WritableLevelData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.function.Supplier;

@Mixin(ServerLevel.class)
public abstract class MixinServerLevel extends Level implements CraftTweakerSavedDataHolder {
    
    protected MixinServerLevel(WritableLevelData $$0, ResourceKey<Level> $$1, Holder<DimensionType> $$2, Supplier<ProfilerFiller> $$3, boolean $$4, boolean $$5, long $$6, int $$7) {
        
        super($$0, $$1, $$2, $$3, $$4, $$5, $$6, $$7);
    }
    
    @Shadow
    public abstract DimensionDataStorage getDataStorage();
    
    @Unique
    public CraftTweakerSavedData crafttweaker$crafttweakerSavedData;
    
    
    @Inject(method = "<init>", at = @At(value = "TAIL"))
    public void crafttweaker$init(MinecraftServer $$0, Executor $$1, LevelStorageSource.LevelStorageAccess $$2, ServerLevelData $$3, ResourceKey $$4, LevelStem $$5, ChunkProgressListener $$6, boolean $$7, long $$8, List $$9, boolean $$10, CallbackInfo ci) {
        
        this.crafttweaker$crafttweakerSavedData = this.getDataStorage()
                .computeIfAbsent(CraftTweakerSavedData::load, CraftTweakerSavedData::new, "crafttweaker_saved_data");
    }
    
    @Override
    public CraftTweakerSavedData crafttweaker$getSavedData() {
        
        return crafttweaker$crafttweakerSavedData;
    }
    
}
