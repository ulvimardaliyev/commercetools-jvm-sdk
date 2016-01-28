package io.sphere.sdk.payments.commands.updateactions;

import io.sphere.sdk.commands.UpdateActionImpl;
import io.sphere.sdk.payments.Payment;

import javax.annotation.Nullable;
import javax.money.MonetaryAmount;

/**
 * Sets the amount of money that has been refunded to the customer. If no amount is provided, the amount is unset.
 *
 * {@doc.gen intro}
 *
 * {@include.example io.sphere.sdk.payments.commands.PaymentUpdateCommandTest#refunded()}
 *
 * <p>Example with multiple refunds:</p>
 * {@include.example io.sphere.sdk.payments.commands.PaymentUpdateCommandTest#multiRefund()}
 *
 * @see Payment
 *
 *
 */
public class SetAmountRefunded extends UpdateActionImpl<Payment> {
    @Nullable
    private final MonetaryAmount amount;

    private SetAmountRefunded(@Nullable final MonetaryAmount amount) {
        super("setAmountRefunded");
        this.amount = amount;
    }

    public static SetAmountRefunded of(@Nullable final MonetaryAmount amount) {
        return new SetAmountRefunded(amount);
    }

    @Nullable
    public MonetaryAmount getAmount() {
        return amount;
    }
}