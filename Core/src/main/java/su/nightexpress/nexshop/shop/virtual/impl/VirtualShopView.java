package su.nightexpress.nexshop.shop.virtual.impl;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import su.nexmedia.engine.api.config.JYML;
import su.nexmedia.engine.api.menu.IMenuClick;
import su.nexmedia.engine.api.menu.IMenuItem;
import su.nexmedia.engine.api.menu.MenuItem;
import su.nexmedia.engine.api.menu.MenuItemType;
import su.nexmedia.engine.utils.StringUtil;
import su.nightexpress.nexshop.api.shop.ShopView;
import su.nightexpress.nexshop.api.type.ShopClickType;
import su.nightexpress.nexshop.api.type.StockType;
import su.nightexpress.nexshop.api.type.TradeType;
import su.nightexpress.nexshop.shop.virtual.VirtualShopModule;
import su.nightexpress.nexshop.shop.virtual.config.VirtualConfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VirtualShopView extends ShopView<VirtualShop> {

    public VirtualShopView(@NotNull VirtualShop shop, @NotNull JYML cfg) {
        super(shop, cfg);

        IMenuClick click = (p, type, e) -> {
            if (type instanceof MenuItemType type2) {
                if (type2 == MenuItemType.RETURN) {
                    VirtualShopModule module = plugin.getVirtualShop();
                    if (module != null && module.hasMainMenu()) {
                        module.openMainMenu(p);
                    }
                    else p.closeInventory();
                }
                else this.onItemClickDefault(p, type2);
            }
        };

        for (String sId : cfg.getSection("Content")) {
            IMenuItem menuItem = cfg.getMenuItem("Content." + sId, MenuItemType.class);

            if (menuItem.getType() != null) {
                menuItem.setClick(click);
            }
            this.addItem(menuItem);
        }
    }

    @Override
    public void displayProducts(@NotNull Player player, @NotNull Inventory inventory, int page) {
        for (VirtualProduct product : shop.getProducts()) {
            if (product.getPage() != page) continue;

            ItemStack preview = product.getPreview();
            ItemMeta meta = preview.getItemMeta();
            if (meta == null) return;

            List<String> loreFormat = VirtualConfig.PRODUCT_FORMAT_LORE_GENERAL_ALL.get();
            if (!product.isBuyable() || !shop.isTransactionEnabled(TradeType.BUY)) {
                loreFormat = VirtualConfig.PRODUCT_FORMAT_LORE_GENERAL_SELL_ONLY.get();
            }
            if (!product.isSellable() || !shop.isTransactionEnabled(TradeType.SELL)) {
                loreFormat = VirtualConfig.PRODUCT_FORMAT_LORE_GENERAL_BUY_ONLY.get();
            }

            List<Component> lore = new ArrayList<>();

            Label_Format:
            for (String lineFormat : loreFormat) {
                if (lineFormat.equalsIgnoreCase("%lore%")) {
                    List<Component> list2 = meta.lore();
                    if (list2 != null) lore.addAll(list2);
                    continue;
                }
                else if (lineFormat.equalsIgnoreCase("%discount%")) {
                    if (this.getShop().hasDiscount(product)) {
                        lore.addAll(StringUtil.asComponent(VirtualConfig.PRODUCT_FORMAT_LORE_DISCOUNT.get()));
                    }
                    continue;
                }
                for (StockType stockType : StockType.values()) {
                    for (TradeType tradeType : TradeType.values()) {
                        if (lineFormat.equalsIgnoreCase("%stock_" + stockType.name() + "_" + tradeType.name() + "%")) {
                            if (!product.getStock().isUnlimited(stockType, tradeType)) {
                                lore.addAll(StringUtil.asComponent(VirtualConfig.PRODUCT_FORMAT_LORE_STOCK.get().getOrDefault(stockType, Collections.emptyMap()).getOrDefault(tradeType, Collections.emptyList())));
                            }
                            continue Label_Format;
                        }
                    }
                }
                lore.add(StringUtil.asComponent(lineFormat));
            }

            lore = StringUtil.applyStringReplacer(product.replacePlaceholders(player), lore);
            lore = StringUtil.applyStringReplacer(product.getCurrency().replacePlaceholders(), lore);
            meta.lore(lore);
            preview.setItemMeta(meta);

            IMenuItem menuItem = new MenuItem(preview);
            menuItem.setSlots(product.getSlot());
            menuItem.setClick((player1, type, e) -> {
                ShopClickType clickType = ShopClickType.getByDefault(e.getClick());
                if (clickType == null) return;

                product.prepareTrade(player1, clickType);
            });
            this.addItem(player, menuItem);
        }

        this.setPage(player, page, this.shop.getPages());
    }
}
