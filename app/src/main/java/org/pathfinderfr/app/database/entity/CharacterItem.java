package org.pathfinderfr.app.database.entity;

public class CharacterItem extends DBEntity {

    public static final int LOCATION_NOLOC    = 0;
    public static final int LOCATION_HEAD     = 1;
    public static final int LOCATION_FOREHEAD = 2;
    public static final int LOCATION_EYE      = 3;
    public static final int LOCATION_NECK     = 4;
    public static final int LOCATION_SHOULDER = 5;
    public static final int LOCATION_TORSO    = 6;
    public static final int LOCATION_ARM      = 7;
    public static final int LOCATION_HAND     = 8;
    public static final int LOCATION_FINGERS  = 9;
    public static final int LOCATION_WAIST    = 10;
    public static final int LOCATION_FOOT     = 11;
    public static final int LOCATION_ARMOR    = 12;
    public static final int LOCATION_SHIELD   = 13;

    public static final int CATEGORY_UNCLASSIFIED  = 0;
    public static final int CATEGORY_EQUIPMENT     = 1;
    public static final int CATEGORY_WEAPON_HAND   = 2;
    public static final int CATEGORY_WEAPON_RANGED = 3;
    public static final int CATEGORY_ARMOR         = 4;
    public static final int CATEGORY_SHIELD        = 5;
    public static final int CATEGORY_CLOTH         = 6;
    public static final int CATEGORY_POTION        = 7;
    public static final int CATEGORY_RING          = 8;
    public static final int CATEGORY_SCEPTER       = 9;
    public static final int CATEGORY_SCROLL        = 10;
    public static final int CATEGORY_STAFF         = 11;
    public static final int CATEGORY_WAND          = 12;
    public static final int CATEGORY_MAGIC         = 13;


    private static final long IDX_WEAPONS    = 0L;
    private static final long IDX_ARMORS     = 1000000L;
    private static final long IDX_EQUIPMENT  = 2000000L;
    private static final long IDX_MAGICITEM  = 3000000L;

    private long characterId;
    private int weight;
    private long price;
    private long itemRef;
    private String ammo;
    private int location;

    private int order;
    private int category;
    private boolean equiped;

    public CharacterItem() {}

    public CharacterItem(long characterId, String name, int weight, long price, long itemRef, String ammo, int category, int location) {
        this.characterId = characterId;
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.itemRef = itemRef;
        this.ammo = ammo;
        this.category = category;
        this.location = location;
        this.order = 0;
    }

//    public CharacterItem(CharacterItem copy) {
//            this.name = copy.name;
//            this.weight = copy.weight;
//            this.price = copy.price;
//            this.objectId = copy.objectId;
//            this.infos = copy.infos;
//    }

    @Override
    public void setReference(String reference) {
        throw new IllegalStateException("Character items have no reference!");
    }

    @Override
    public void setSource(String source) {
        throw new IllegalStateException("Character items have no reference!");
    }

    public long getCharacterId() {
        return characterId;
    }

    public void setCharacterId(long characterId) {
        this.characterId = characterId;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getItemRef() {
        return itemRef;
    }

    public long getOriginalItemRef() {
        if(isNotLinked()) {
            return itemRef;
        } else if(isWeapon()) {
            return itemRef - CharacterItem.IDX_WEAPONS;
        } else if(isArmor()) {
            return itemRef - CharacterItem.IDX_ARMORS;
        } else if(isEquipment()) {
            return itemRef - CharacterItem.IDX_EQUIPMENT;
        } else if(isMagicItem()) {
            return itemRef - CharacterItem.IDX_MAGICITEM;
        }
        return itemRef;
    }

    public void setItemRef(long itemRef) {
        this.itemRef = itemRef;
    }

    public String getAmmo() {
        return ammo;
    }

    public void setAmmo(String ammo) {
        this.ammo = ammo;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location < LOCATION_NOLOC || location > LOCATION_SHIELD ? LOCATION_NOLOC : location;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isValid() {
        return name != null && name.length() >= 3 && weight >= 0;
    }

    public boolean isNotLinked() {
        return itemRef <= 0;
    }

    public boolean isWeapon() {
        return itemRef > IDX_WEAPONS && itemRef < IDX_ARMORS;
    }

    public boolean isArmor() {
        return itemRef > IDX_ARMORS && itemRef < IDX_EQUIPMENT;
    }

    public boolean isEquipment() {
        return itemRef > IDX_EQUIPMENT && itemRef < IDX_MAGICITEM;
    }

    public boolean isMagicItem() {
        return itemRef > IDX_MAGICITEM;
    }

    public boolean isEquiped() {
        return equiped;
    }

    public void setEquiped(boolean equiped) {
        this.equiped = equiped;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    @Override
    public DBEntityFactory getFactory() {
        return CharacterItemFactory.getInstance();
    }

    /**
     * Change default ordering (by name) to use order field
     */
    @Override
    public int compareTo(DBEntity o) {
        if(o instanceof CharacterItem) {
            CharacterItem ci = (CharacterItem)o;
            if(order == ci.order) {
                return super.compareTo(o);
            } else {
                return Integer.compare(order, ci.order);
            }
        }
        return super.compareTo(o);
    }
}
