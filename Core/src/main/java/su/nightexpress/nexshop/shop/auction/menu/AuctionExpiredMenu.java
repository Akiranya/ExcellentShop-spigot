package su.nightexpress.nexshop.shop.auction.menu;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import su.nexmedia.engine.api.config.JYML;
import su.nexmedia.engine.api.menu.click.ItemClick;
import su.nightexpress.nexshop.shop.auction.AuctionManager;
import su.nightexpress.nexshop.shop.auction.listing.AuctionListing;

import java.util.*;

public class AuctionExpiredMenu extends AbstractAuctionMenu<AuctionListing> {

    public AuctionExpiredMenu(@NotNull AuctionManager auctionManager, @NotNull JYML cfg) {
        super(auctionManager, cfg);

        this.registerHandler(ItemType.class)
            .addClick(ItemType.TAKE_ALL, (viewer, event) -> {
                Player player = viewer.getPlayer();
                this.auctionManager.getExpiredListings(player).forEach(listing -> {
                    this.auctionManager.takeListing(player, listing);
                });
                this.openNextTick(viewer, viewer.getPage());
            });

        this.load();
    }

    private enum ItemType {
        TAKE_ALL
    }

    @Override
    public @NotNull List<AuctionListing> getObjects(@NotNull Player player) {
        UUID id = this.seeOthers.getOrDefault(player, player.getUniqueId());
        return this.auctionManager.getExpiredListings(id);
    }

    @Override
    public @NotNull ItemClick getObjectClick(@NotNull AuctionListing item) {
        return (viewer, event) -> {
            Player player = viewer.getPlayer();
            this.auctionManager.takeListing(player, item);
            this.openNextTick(viewer, viewer.getPage());
        };
    }
}
