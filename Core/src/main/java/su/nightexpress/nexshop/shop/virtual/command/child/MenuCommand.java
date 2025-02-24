package su.nightexpress.nexshop.shop.virtual.command.child;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import su.nexmedia.engine.utils.CollectionsUtil;
import su.nightexpress.nexshop.shop.module.ModuleCommand;
import su.nightexpress.nexshop.shop.virtual.VirtualShopModule;
import su.nightexpress.nexshop.shop.virtual.config.VirtualLang;
import su.nightexpress.nexshop.shop.virtual.config.VirtualPerms;
import su.nightexpress.nexshop.shop.virtual.menu.ShopMainMenu;

import java.util.List;
import java.util.Map;

public class MenuCommand extends ModuleCommand<VirtualShopModule> {

    public MenuCommand(@NotNull VirtualShopModule module) {
        super(module, new String[]{"menu"}, VirtualPerms.COMMAND_MENU);
    }

    @Override
    public @NotNull String getDescription() {
        return plugin.getMessage(VirtualLang.COMMAND_MENU_DESC).getLocalized();
    }

    @Override
    public @NotNull String getUsage() {
        return plugin.getMessage(VirtualLang.COMMAND_MENU_USAGE).getLocalized();
    }

    @Override
    public boolean isPlayerOnly() {
        return false;
    }

    @Override
    public @NotNull List<String> getTab(@NotNull Player player, int arg, @NotNull String[] args) {
        if (arg == 1) {
            return CollectionsUtil.playerNames(player);
        }
        return super.getTab(player, arg, args);
    }

    @Override
    public void onExecute(@NotNull CommandSender sender, @NotNull String label, @NotNull String[] args, @NotNull Map<String, String> flags) {
        String pName = args.length >= 2 ? args[1] : sender.getName();
        Player player = plugin.getServer().getPlayer(pName);
        if (player == null) {
            this.errorPlayer(sender);
            return;
        }

        ShopMainMenu mainMenu = this.module.getMainMenu();
        if (mainMenu == null) return;

        mainMenu.open(player, 1);
    }
}
