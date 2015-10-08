package io.sphere.sdk.payments;

import io.sphere.sdk.customers.Customer;
import io.sphere.sdk.models.Reference;
import io.sphere.sdk.types.CustomFields;
import io.sphere.sdk.types.CustomFieldsDraft;

import javax.annotation.Nullable;
import javax.money.MonetaryAmount;
import java.time.ZonedDateTime;
import java.util.List;

public interface PaymentDraft {
    @Nullable
    Reference<Customer> getCustomer();

    @Nullable
    String getExternalId();

    @Nullable
    String getInterfaceId();

    MonetaryAmount getAmountPlanned();

    @Nullable
    MonetaryAmount getAmountAuthorized();

    @Nullable
    ZonedDateTime getAuthorizedUntil();

    @Nullable
    MonetaryAmount getAmountPaid();

    @Nullable
    MonetaryAmount getAmountRefunded();

    PaymentMethodInfo getPaymentMethodInfo();

    @Nullable
    CustomFieldsDraft getCustom();

    PaymentStatus getPaymentStatus();

    List<Transaction> getTransactions();

    List<CustomFields> getInterfaceInteractions();
}
