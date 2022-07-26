package com.vntly.rebud.core.event.loot;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;

public class ShavingsDropFromStoneAdditionModifier extends LootModifier {
    private final Item addition;
    private final Item addition0;
    private final Item addition1;
    //private int
    protected ShavingsDropFromStoneAdditionModifier(LootItemCondition[] conditionsIn, Item addition, Item addition0, Item addition1) {
        super(conditionsIn);
        this.addition = addition;
        this.addition0 = addition0;
        this.addition1 = addition1;
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        // generatedLoot is the loot that would be dropped, if we wouldn't add or replace
        // anything!
        int b = context.getRandom().nextInt(1, 4);
        float a = context.getRandom().nextFloat();
        if(a > 0.10f && a <= 0.15f) {
            generatedLoot.add(new ItemStack(addition, b));
        } else if(a > 0.45f && a <= 0.48f) {
            generatedLoot.add(new ItemStack(addition0, b));
        } else if(a > 0.98f) {
            generatedLoot.add(new ItemStack(addition1, b));
        }
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<ShavingsDropFromStoneAdditionModifier> {
        @Override
        public ShavingsDropFromStoneAdditionModifier read(ResourceLocation name, JsonObject object,
                                                          LootItemCondition[] conditionsIn) {
            Item addition = ForgeRegistries.ITEMS.getValue(
                    new ResourceLocation(GsonHelper.getAsString(object, "addition")));
            Item addition0 = ForgeRegistries.ITEMS.getValue(
                    new ResourceLocation(GsonHelper.getAsString(object, "addition0")));
            Item addition1 = ForgeRegistries.ITEMS.getValue(
                    new ResourceLocation(GsonHelper.getAsString(object, "addition1")));
            return new ShavingsDropFromStoneAdditionModifier(conditionsIn, addition, addition0, addition1);
        }

        @Override
        public JsonObject write(ShavingsDropFromStoneAdditionModifier instance) {
            JsonObject json = makeConditions(instance.conditions);
            json.addProperty("addition", ForgeRegistries.ITEMS.getKey(instance.addition).toString());
            json.addProperty("addition0", ForgeRegistries.ITEMS.getKey(instance.addition).toString());
            json.addProperty("addition1", ForgeRegistries.ITEMS.getKey(instance.addition).toString());

            return json;
        }
    }
}
