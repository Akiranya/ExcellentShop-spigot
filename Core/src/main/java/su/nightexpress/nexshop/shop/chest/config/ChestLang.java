package su.nightexpress.nexshop.shop.chest.config;

import su.nexmedia.engine.api.lang.LangColors;
import su.nexmedia.engine.api.lang.LangKey;

public class ChestLang implements LangColors {

    public static final LangKey COMMAND_LIST_DESC = LangKey.of("ChestShop.Command.List.Desc", "Browse your own or other player's shops.");
    public static final LangKey COMMAND_LIST_USAGE = LangKey.of("ChestShop.Command.List.Usage", "[player]");
    public static final LangKey COMMAND_CREATE_DESC = LangKey.of("ChestShop.Command.Create.Desc", "Creates shop of the chest that you're looking on.");
    public static final LangKey COMMAND_CREATE_USAGE = LangKey.of("ChestShop.Command.Create.Usage", "[type]");
    public static final LangKey COMMAND_SEARCH_DESC = LangKey.of("ChestShop.Command.Search.Desc", "Search for shops with specified item.");
    public static final LangKey COMMAND_SEARCH_USAGE = LangKey.of("ChestShop.Command.Search.Usage", "<material>");
    public static final LangKey COMMAND_REMOVE_DESC = LangKey.of("ChestShop.Command.Remove.Desc", "Removes the shop from the chest that you're looking at.");

    public static final LangKey SHOP_ERROR_NOT_OWNER = LangKey.of("ChestShop.Shop.Error.NotOwner", "<red>You're not the owner of this shop!");
    public static final LangKey SHOP_CREATION_INFO_DONE = LangKey.of("ChestShop.Shop.Creation.Info.Done", "{message: ~type: TITLES; ~fadeIn: 10; ~stay: 80; ~fadeOut: 10; ~sound: BLOCK_NOTE_BLOCK_BELL;}<green><b>Shop Created!\n<gray>Use <green>Shift-Click</green> to enter in <green>Edit Mode</green>.");
    public static final LangKey SHOP_CREATION_ERROR_ALREADY_SHOP = LangKey.of("ChestShop.Shop.Creation.Error.AlreadyShop", "This chest is already shop!");
    public static final LangKey SHOP_CREATION_ERROR_NOT_EMPTY = LangKey.of("ChestShop.Shop.Creation.Error.NotEmpty", "Please remove all items from the chest first.");
    public static final LangKey SHOP_CREATION_ERROR_NOT_A_CHEST = LangKey.of("ChestShop.Shop.Creation.Error.NotAChest", "This block is not a chest!");
    public static final LangKey SHOP_CREATION_ERROR_BAD_LOCATION = LangKey.of("ChestShop.Shop.Creation.Error.BadLocation", "You can't create shop here!");
    public static final LangKey SHOP_CREATION_ERROR_LIMIT_REACHED = LangKey.of("ChestShop.Shop.Creation.Error.LimitReached", "You have reached the limit of shops! You can't create more.");
    public static final LangKey SHOP_CREATION_ERROR_BAD_AREA = LangKey.of("ChestShop.Shop.Creation.Error.BadArea", "You can create shops only inside your own claim!");
    public static final LangKey SHOP_CREATION_ERROR_NOT_ENOUGH_FUNDS = LangKey.of("ChestShop.Shop.Creation.Error.NotEnoughFunds", "You don't have enough funds!");
    public static final LangKey SHOP_CREATION_ERROR_TYPE_PERMISSION = LangKey.of("ChestShop.Shop.Creation.Error.TypePermission", "You don't have permission to create this type shops!");
    public static final LangKey SHOP_REMOVAL_INFO_DONE = LangKey.of("ChestShop.Shop.Removal.Info.Done", "{message: ~type: TITLES; ~fadeIn: 10; ~stay: 80; ~fadeOut: 10; ~sound: ENTITY_GENERIC_EXPLODE;}<red><b>Shop Removed.");
    public static final LangKey SHOP_REMOVAL_ERROR_NOT_A_SHOP = LangKey.of("ChestShop.Shop.Removal.Error.NotAShop", "This block is not a shop!");
    public static final LangKey SHOP_PRODUCT_ERROR_BAD_ITEM = LangKey.of("ChestShop.Product.Error.BadItem", "This item can not be traded!");
    public static final LangKey SHOP_TRADE_BUY_INFO_USER = new LangKey("ChestShop.Shop.Trade.Buy.Info.User", "{message: ~prefix: false;}<yellow>You bought <gold>x%amount% %item%</gold> for <gold>%price%</gold> from <gold>%shop_name%</gold> shop owned by <gold>%shop_owner%</gold>.");
    public static final LangKey SHOP_TRADE_BUY_INFO_OWNER = new LangKey("ChestShop.Shop.Trade.Buy.Info.Owner", "{message: ~prefix: false;}<yellow><gold>%player%</gold> just bought <gold>x%amount% %item%</gold> for <gold>%price%</gold> from your <gold>%shop_name%</gold> shop.");
    public static final LangKey SHOP_TRADE_SELL_INFO_USER = new LangKey("ChestShop.Shop.Trade.Sell.Info.User", "{message: ~prefix: false;}<yellow>You sold <gold>x%amount% %item%</gold> for <gold>%price%</gold> to <gold>%shop_name%</gold> shop owned by <gold>%shop_owner%</gold>.");
    public static final LangKey SHOP_TRADE_SELL_INFO_OWNER = new LangKey("ChestShop.Shop.Trade.Sell.Info.Owner", "{message: ~prefix: false;}<yellow><gold>%player%</gold> just sold <gold>x%amount% %item%</gold> for <gold>%price%</gold> to your <gold>%shop_name%</gold> shop.");
    public static final LangKey SHOP_BANK_ERROR_INVALID_CURRENCY = new LangKey("ChestShop.Shop.Bank.Error.InvalidCurrency",
        """
        {message: ~type: TITLES; ~fadeIn: 10; ~stay: 80; ~fadeOut: 10;}
        <red><b>Operation Failed!
        <gray>This currency is invalid or is not allowed!
        """);
    public static final LangKey SHOP_BANK_DEPOSIT_SUCCESS = new LangKey("ChestShop.Shop.Bank.Deposit.Success",
        """
        {message: ~type: TITLES; ~fadeIn: 10; ~stay: 80; ~fadeOut: 10;}
        <green><b>Successful Deposit!
        <gray>You deposit <green>%amount%</green> to shop bank!
        """);
    public static final LangKey SHOP_BANK_DEPOSIT_ERROR_NOT_ENOUGH = new LangKey("ChestShop.Shop.Bank.Deposit.Error.NotEnough",
        """
        {message: ~type: TITLES; ~fadeIn: 10; ~stay: 80; ~fadeOut: 10;}
        <red><b>Unable to Deposit!
        <gray>You don't have enough funds!
        """);
    public static final LangKey SHOP_BANK_WITHDRAW_SUCCESS = new LangKey("ChestShop.Shop.Bank.Withdraw.Success",
        """
        {message: ~type: TITLES; ~fadeIn: 10; ~stay: 80; ~fadeOut: 10;}
        <green><b>Successful Withdraw!
        <gray>You withdraw <green>%amount%</green> from shop bank!
        """);
    public static final LangKey SHOP_BANK_WITHDRAW_ERROR_NOT_ENOUGH = new LangKey("ChestShop.Shop.Bank.Withdraw.NotEnough",
        """
        {message: ~type: TITLES; ~fadeIn: 10; ~stay: 80; ~fadeOut: 10;}
        <red><b>Unable to Withdraw!
        <gray>Bank don't have enough funds!
        """);
    public static final LangKey EDITOR_ERROR_PRODUCT_LEFT = LangKey.of("ChestShop.Editor.Error.ProductLeft", "<red>First you have to take all of this product from the chest!");
}
