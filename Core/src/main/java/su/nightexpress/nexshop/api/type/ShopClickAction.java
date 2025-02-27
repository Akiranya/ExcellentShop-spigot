package su.nightexpress.nexshop.api.type;

import org.jetbrains.annotations.NotNull;

public enum ShopClickAction {

    BUY_SELECTION(TradeType.BUY),
    SELL_SELECTION(TradeType.SELL),
    SELL_ALL(TradeType.SELL),
    BUY_SINGLE(TradeType.BUY),
    SELL_SINGLE(TradeType.SELL),
    ;

    private final TradeType tradeType;

    ShopClickAction(@NotNull TradeType tradeType) {
        this.tradeType = tradeType;
    }

    public @NotNull TradeType getTradeType() {
        return this.tradeType;
    }
}
