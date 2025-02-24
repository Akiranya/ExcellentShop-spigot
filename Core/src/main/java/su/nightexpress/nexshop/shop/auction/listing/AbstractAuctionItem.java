package su.nightexpress.nexshop.shop.auction.listing;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import su.nexmedia.engine.api.placeholder.Placeholder;
import su.nexmedia.engine.api.placeholder.PlaceholderMap;
import su.nexmedia.engine.utils.ComponentUtil;
import su.nexmedia.engine.utils.ItemUtil;
import su.nexmedia.engine.utils.TimeUtil;
import su.nightexpress.nexshop.api.currency.Currency;
import su.nightexpress.nexshop.shop.auction.Placeholders;
import su.nightexpress.nexshop.shop.auction.config.AuctionConfig;

import java.util.*;

public abstract class AbstractAuctionItem implements Placeholder {

    protected final UUID id;
    protected UUID owner;
    protected String ownerName;
    protected ItemStack itemStack;
    protected double price;
    protected final Currency currency;
    protected final long dateCreation;
    protected PlaceholderMap placeholderMap;

    public AbstractAuctionItem(
        @NotNull UUID id,
        @NotNull UUID owner,
        @NotNull String ownerName,
        @NotNull ItemStack itemStack,
        Currency currency,
        double price,
        long dateCreation
    ) {
        this.id = id;
        this.owner = owner;
        this.ownerName = ownerName;
        this.itemStack = itemStack.clone();
        this.currency = currency;
        this.price = price;
        this.dateCreation = dateCreation;
        this.placeholderMap = new PlaceholderMap()
            .add(Placeholders.LISTING_SELLER, this.getOwnerName())
            .add(Placeholders.LISTING_PRICE, this.getCurrency().format(this.getPrice()))
            .add(Placeholders.LISTING_DATE_CREATION, AuctionConfig.DATE_FORMAT.format(TimeUtil.getLocalDateTimeOf(this.getDateCreation())))
            .add(Placeholders.LISTING_ITEM_AMOUNT, String.valueOf(this.getItemStack().getAmount()))
            .add(Placeholders.LISTING_ITEM_NAME, ComponentUtil.asMiniMessage(ItemUtil.getName(this.getItemStack())))
            /*.add(Placeholders.LISTING_ITEM_LORE, String.join("\n", ComponentUtil.asMiniMessage(ItemUtil.getLore(this.getItemStack()))))*/
            .add(Placeholders.LISTING_ITEM_VALUE, String.valueOf(ItemUtil.toBase64(this.getItemStack())))
            .add(Placeholders.LISTING_DELETES_IN, () -> TimeUtil.formatTimeLeft(this.getDeleteDate()))
            .add(Placeholders.LISTING_DELETE_DATE, AuctionConfig.DATE_FORMAT.format(TimeUtil.getLocalDateTimeOf(this.getDeleteDate())));
    }

    @Override
    public @NotNull PlaceholderMap getPlaceholders() {
        return this.placeholderMap;
    }

    public final @NotNull UUID getId() {
        return id;
    }

    public @NotNull UUID getOwner() {
        return owner;
    }

    public boolean isOwner(@NotNull UUID uuid) {
        return this.getOwner().equals(uuid);
    }

    public @NotNull String getOwnerName() {
        return ownerName;
    }

    public @NotNull ItemStack getItemStack() {
        return itemStack;
    }

    public @NotNull Currency getCurrency() {
        return currency;
    }

    public double getPrice() {
        return price;
    }

    public boolean isOwner(@NotNull Player player) {
        return player.getUniqueId().equals(this.getOwner());
    }

    public long getDateCreation() {
        return dateCreation;
    }

    public abstract long getDeleteDate();
}
