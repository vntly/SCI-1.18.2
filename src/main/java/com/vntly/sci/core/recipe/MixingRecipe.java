package com.vntly.sci.core.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.vntly.sci.SCIMod;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class MixingRecipe implements Recipe<Container> {
    private final ResourceLocation id;
    private final ItemStack output;
    private final int time;
    private final NonNullList<Ingredient> recipeItems;

    public MixingRecipe(ResourceLocation id, ItemStack output,
                        int time, NonNullList<Ingredient> recipeItems) {
        this.id = id;
        this.output = output;
        this.time = time;
        this.recipeItems = recipeItems;
    }


    @Override
    public boolean matches(Container p_44002_, Level p_44003_) {
        return recipeItems.get(0).test(p_44002_.getItem(0));
    }

    @Override
    public ItemStack assemble(Container p_44001_) {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return output.copy();
    }

    public int getCookingTime(){return time;}

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<MixingRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "mixing";
    }

    public static class Serializer implements RecipeSerializer<MixingRecipe> {
        public static final MixingRecipe.Serializer INSTANCE = new MixingRecipe.Serializer();
        public static final ResourceLocation ID =
                new ResourceLocation(SCIMod.MODID,"mixing");

        @Override
        public MixingRecipe fromJson(ResourceLocation id, JsonObject json) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));
            int time = GsonHelper.getAsInt(json, "time");

            JsonArray ingredients = GsonHelper.getAsJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new MixingRecipe(id, output, time, inputs);
        }

        @Override
        public MixingRecipe  fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buf));
            }
            int time = buf.readVarInt();

            ItemStack output = buf.readItem();
            return new MixingRecipe(id, output, time, inputs);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, MixingRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }
            buf.writeVarInt(recipe.time);
            buf.writeItemStack(recipe.getResultItem(), false);
        }

        @Override
        public RecipeSerializer<?> setRegistryName(ResourceLocation name) {
            return INSTANCE;
        }

        @Nullable
        @Override
        public ResourceLocation getRegistryName() {
            return ID;
        }

        @Override
        public Class<RecipeSerializer<?>> getRegistryType() {
            return MixingRecipe.Serializer.castClass(RecipeSerializer.class);
        }

        @SuppressWarnings("unchecked") // Need this wrapper, because generics
        private static <G> Class<G> castClass(Class<?> cls) {
            return (Class<G>)cls;
        }
    }
}