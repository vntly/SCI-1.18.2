package com.vntly.sci.core.util;

import com.vntly.sci.SCIMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> DIGGER_VALUABLES = tag("digger_valuables");

        private static TagKey<Block> tag(String name){
            return BlockTags.create(new ResourceLocation(SCIMod.MODID, name));
        }

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }

    }

    public static class Items {

        public static final TagKey<Item> CHISELS = tag("chisels");
        public static final TagKey<Item> FOSSILS = tag("fossils");
        public static final TagKey<Item> BASE= tag("base");
        public static final TagKey<Item> HARD_FOSSILS = tag("hard_fossils");
        public static final TagKey<Item> ADDITION = tag("addition");
        private static TagKey<Item> tag(String name){
            return ItemTags.create(new ResourceLocation(SCIMod.MODID, name));
        }

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }
}
