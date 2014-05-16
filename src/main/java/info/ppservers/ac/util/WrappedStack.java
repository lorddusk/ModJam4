package info.ppservers.ac.util;

import com.google.gson.*;
import info.ppservers.ac.ModInformation;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Tim on 5/16/2014.
 */
public class WrappedStack implements Comparable<WrappedStack>, JsonDeserializer<WrappedStack>, JsonSerializer<WrappedStack> {

    private static final Gson gsonSerializer = (new GsonBuilder()).registerTypeAdapter(WrappedStack.class, new WrappedStack()).create();
    private final String className;
    private int stackSize;
    private final Object wrappedStack;

    private static Logger log = Logger.getLogger(ModInformation.ID);

    public WrappedStack() {
        className = null;
        stackSize = -1;
        wrappedStack = null;
    }

    public WrappedStack(Object object) {
        if (object instanceof Item) {
            object = new ItemStack((Item) object);
        } else if (object instanceof Block) {
            object = new ItemStack((Block) object);
        }
        if (object instanceof ItemStack) {
            ItemStack itemStack = ((ItemStack) object).copy();

            className = ItemStack.class.getSimpleName();
            stackSize = itemStack.stackSize;
            itemStack.stackSize = 1;
            wrappedStack = itemStack;
        } else if (object instanceof WrappedStack) {
            WrappedStack wrappedStackObject = (WrappedStack) object;
            if (wrappedStackObject.getWrappedStack() != null) {
                className = wrappedStackObject.wrappedStack.getClass().getSimpleName();
                this.stackSize = wrappedStackObject.stackSize;
                this.wrappedStack = wrappedStackObject.wrappedStack;
            } else {
                className = null;
                stackSize = -1;
                wrappedStack = null;
            }
        } else if (object instanceof String) {
            WrappedStack wrappedStack = createFromJson((String) object);
            if (wrappedStack != null && wrappedStack.getWrappedStack() != null) {
                className = wrappedStack.getWrappedStack().getClass().getSimpleName();
                stackSize = wrappedStack.stackSize;
                this.wrappedStack = wrappedStack.wrappedStack;
            } else {
                className = null;
                stackSize = -1;
                this.wrappedStack = null;
            }
        } else {
            className = null;
            stackSize = -1;
            this.wrappedStack = null;
        }
    }

    public WrappedStack(Object object, int stackSize) {
        if (object instanceof Item) {
            object = new ItemStack((Item) object);
        } else if (object instanceof Block) {
            object = new ItemStack((Block) object);
        }
        if (object instanceof ItemStack) {
            ItemStack itemStack = ((ItemStack) object).copy();

            className = ItemStack.class.getSimpleName();
            this.stackSize = stackSize;
            itemStack.stackSize = 1;
            wrappedStack = itemStack;
        } else if (object instanceof WrappedStack) {
            WrappedStack wrappedStackObject = (WrappedStack) object;

            if (wrappedStackObject.getWrappedStack() != null) {
                className = wrappedStackObject.wrappedStack.getClass().getSimpleName();
                this.stackSize = stackSize;
                this.wrappedStack = wrappedStackObject.wrappedStack;
            } else {
                className = null;
                this.stackSize = -1;
                wrappedStack = null;
            }
        } else if (object instanceof String) {
            WrappedStack wrappedStack = createFromJson((String) object);

            if (wrappedStack != null && wrappedStack.getWrappedStack() != null) {
                className = wrappedStack.wrappedStack.getClass().getSimpleName();
                this.stackSize = stackSize;
                this.wrappedStack = wrappedStack.wrappedStack;
            } else {
                className = null;
                this.stackSize = -1;
                this.wrappedStack = null;
            }
        } else {
            className = null;
            this.stackSize = -1;
            wrappedStack = null;
        }
    }

    public int getStackSize() {
        return stackSize;
    }

    public void steStackSize(int stackSize) {
        this.stackSize = stackSize;
    }

    public Object getWrappedStack() {
        return wrappedStack;
    }

    public static WrappedStack createFromJson(String jsonWrappedObject) throws JsonParseException {
        try {
            return gsonSerializer.fromJson(jsonWrappedObject, WrappedStack.class);
        } catch (JsonSyntaxException e) {
            log.log(Level.SEVERE, e.getMessage());
        } catch (JsonParseException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
        return null;
    }

    public String toJson() {
        return gsonSerializer.toJson(this);
    }

    @Override
    public int compareTo(WrappedStack wrappedStack) {
        return comparator.compare(this, wrappedStack);
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        hashCode = (37 * hashCode) + stackSize;

        if (wrappedStack instanceof ItemStack) {
            hashCode = (37 * hashCode) + ((ItemStack) wrappedStack).getItemDamage();
            if (((ItemStack) wrappedStack).getTagCompound() != null) {
                hashCode = (37 * hashCode) + ((ItemStack) wrappedStack).getTagCompound().hashCode();
            }
        }
        return hashCode;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof WrappedStack && (this.compareTo((WrappedStack) object) == 0);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (wrappedStack instanceof ItemStack) {
            ItemStack itemStack = (ItemStack) wrappedStack;
            try {
                stringBuilder.append(String.format("$sxitemStack[%s:%s:%s:%s", stackSize, itemStack.getItem(), itemStack.getItemDamage(), itemStack.getUnlocalizedName(), itemStack.getItem().getClass().getCanonicalName()));
            } catch (ArrayIndexOutOfBoundsException e) {

            }
        } else {
            stringBuilder.append("null");
        }
        return stringBuilder.toString();
    }

    public static boolean canBeWrapped(Object object) {
        if (object instanceof WrappedStack) {
            return true;
        } else if (object instanceof Item || object instanceof Block || object instanceof ItemStack) {
            return true;
        }
        return false;
    }

    @Override
    public WrappedStack deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        if (!jsonElement.isJsonPrimitive()) {
            JsonObject jsonWrappedStack = (JsonObject) jsonElement;

            int stackSize = -1;
            String className = null;
            Object stackObject = null;

            if (jsonWrappedStack.get("className") != null) {
                className = jsonWrappedStack.get("className").getAsString();
            }
            if (jsonWrappedStack.get("stackSize") != null) {
                stackSize = jsonWrappedStack.get("stackSize").getAsInt();
            }
            if (jsonWrappedStack.get("wrappedStack") != null && !jsonWrappedStack.get("wrappedStack").isJsonPrimitive()) {
                if (className != null) {
                    if (className.equalsIgnoreCase(ItemStack.class.getSimpleName())) {
                        JsonItemStack jsonItemStack = gsonSerializer.fromJson(jsonWrappedStack.get("wrappedStack"), JsonItemStack.class);
                        ItemStack itemStack = null;
                        if (stackSize > 0) {
                            itemStack = new ItemStack(jsonItemStack.item, stackSize, jsonItemStack.itemDamage);
                            if (jsonItemStack.compressedStackTagCompound != null) {
                                try {
                                    itemStack.stackTagCompound = CompressedStreamTools.decompress(jsonItemStack.compressedStackTagCompound);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        stackObject = itemStack;
                    }
                }
            }
            if (stackObject != null) {
                return new WrappedStack(stackObject);
            } else {
                throw new JsonParseException(String.format("Unable to parse a wrappable stack object from the provided json: %s", jsonElement.toString()));
            }
        } else {
            throw new JsonParseException(String.format("Unable to parse a wrappable stack object from the provided json: %s", jsonElement.toString()));
        }
    }

    @Override
    public JsonElement serialize(WrappedStack wrappedStack, Type type, JsonSerializationContext context) {
        JsonObject jsonWrappedStack = new JsonObject();
        Gson gsonWrappedStack = new Gson();

        jsonWrappedStack.addProperty("className", wrappedStack.className);
        jsonWrappedStack.addProperty("stackSize", wrappedStack.stackSize);

        if (wrappedStack.wrappedStack instanceof ItemStack) {
            JsonItemStack jsonItemStack = new JsonItemStack();
            jsonItemStack.item = (((ItemStack) wrappedStack.wrappedStack).getItem());
            jsonItemStack.itemDamage = (((ItemStack) wrappedStack.wrappedStack).getItemDamage());
            jsonItemStack.stackSize = (((ItemStack) wrappedStack.wrappedStack).stackSize);
            if (((ItemStack) wrappedStack.wrappedStack).stackTagCompound != null) {
                try {
                    jsonItemStack.compressedStackTagCompound = CompressedStreamTools.compress(((ItemStack) wrappedStack.wrappedStack).stackTagCompound);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            jsonWrappedStack.add("wrappedStack", gsonWrappedStack.toJsonTree(jsonItemStack, JsonItemStack.class));
        }
        return jsonWrappedStack;
    }

    public static Comparator<WrappedStack> comparator = new Comparator<WrappedStack>() {
        @Override
        public int compare(WrappedStack wrappedStack1, WrappedStack wrappedStack2) {
            if (wrappedStack1.wrappedStack instanceof ItemStack) {
                if (wrappedStack2.wrappedStack instanceof ItemStack) {
                    return ItemHelper.compare((ItemStack) wrappedStack1.wrappedStack, (ItemStack) wrappedStack2.wrappedStack);
                }else{
                    return 1;
                }
            }
            else if(wrappedStack1.wrappedStack == null ){
                if(wrappedStack2.wrappedStack != null){
                    return -1;
                }else{
                    return 0;
                }
            }
            return 0;
        }
    };

}
