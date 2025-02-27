package su.nightexpress.nexshop.shop.auction.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.nexmedia.engine.utils.CollectionsUtil;
import su.nightexpress.nexshop.data.user.ShopUser;
import su.nightexpress.nexshop.shop.auction.AuctionManager;
import su.nightexpress.nexshop.shop.auction.menu.AbstractAuctionMenu;
import su.nightexpress.nexshop.shop.module.ModuleCommand;

import java.util.List;
import java.util.Map;
import java.util.UUID;

abstract class AbstractOpenCommand extends ModuleCommand<AuctionManager> {

    public AbstractOpenCommand(@NotNull AuctionManager module, @NotNull String[] aliases, @Nullable Permission permission) {
        super(module, aliases, permission);
    }

    protected abstract @NotNull AbstractAuctionMenu<?> getMenu();

    protected abstract @Nullable Permission getPermissionsOthers();

    @Override
    public boolean isPlayerOnly() {
        return true;
    }

    @Override
    public @NotNull List<@NotNull String> getTab(@NotNull Player player, int i, @NotNull String[] args) {
        if (i == 1) {
            return CollectionsUtil.playerNames(player);
        }
        return super.getTab(player, i, args);
    }

    @Override
    protected void onExecute(@NotNull CommandSender sender, @NotNull String label, @NotNull String[] args, @NotNull Map<String, String> flags) {
        Player player = (Player) sender;
        if (!this.module.canBeUsedHere(player)) return;

        UUID uuid = player.getUniqueId();
        if (args.length >= 2) {
            if (this.getPermissionsOthers() != null && !sender.hasPermission(this.getPermissionsOthers())) {
                this.errorPermission(sender);
                return;
            }

            ShopUser user = plugin.getUserManager().getUserData(args[1]);
            if (user == null) {
                this.errorPlayer(sender);
                return;
            }
            uuid = user.getId();
        }

        this.getMenu().open(player, 1, uuid);
    }
}
