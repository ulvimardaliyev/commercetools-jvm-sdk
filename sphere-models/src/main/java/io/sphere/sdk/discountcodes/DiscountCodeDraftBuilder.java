package io.sphere.sdk.discountcodes;

import io.sphere.sdk.cartdiscounts.CartDiscount;
import io.sphere.sdk.cartdiscounts.CartDiscountPredicate;
import io.sphere.sdk.models.Base;
import io.sphere.sdk.models.Builder;
import io.sphere.sdk.models.LocalizedStrings;
import io.sphere.sdk.models.Reference;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class DiscountCodeDraftBuilder extends Base implements Builder<DiscountCodeDraft> {
    @Nullable
    private LocalizedStrings name;
    @Nullable
    private LocalizedStrings description;
    private String code;
    private List<Reference<CartDiscount>> cartDiscounts = Collections.emptyList();
    @Nullable
    private CartDiscountPredicate cartPredicate;
    private boolean isActive = true;
    @Nullable
    private Long maxApplications;
    @Nullable
    private Long maxApplicationsPerCustomer;

    private DiscountCodeDraftBuilder(final String code) {
        this.code = code;
    }

    public static DiscountCodeDraftBuilder of(final DiscountCodeDraft template) {
        return of(template.getCode())
                .name(template.getName())
                .description(template.getDescription())
                .cartDiscounts(template.getCartDiscounts())
                .cartPredicate(template.getCartPredicate().map(CartDiscountPredicate::of))
                .isActive(template.isActive())
                .maxApplications(template.getMaxApplications())
                .maxApplicationsPerCustomer(template.getMaxApplicationsPerCustomer());
    }

    public static DiscountCodeDraftBuilder of(final String code) {
        return new DiscountCodeDraftBuilder(code);
    }

    public DiscountCodeDraftBuilder name(final Optional<LocalizedStrings> name) {
        this.name = name.orElse(null);
        return this;
    }

    public DiscountCodeDraftBuilder name(final LocalizedStrings name) {
        return name(Optional.of(name));
    }

    public DiscountCodeDraftBuilder description(final Optional<LocalizedStrings> description) {
        this.description = description.orElse(null);
        return this;
    }

    public DiscountCodeDraftBuilder description(final LocalizedStrings description) {
        return description(Optional.of(description));
    }

    public DiscountCodeDraftBuilder code(final String code) {
        this.code = code;
        return this;
    }

    public DiscountCodeDraftBuilder cartDiscounts(final List<Reference<CartDiscount>> cartDiscounts) {
        this.cartDiscounts = cartDiscounts;
        return this;
    }

    public DiscountCodeDraftBuilder cartPredicate(final Optional<CartDiscountPredicate> cartPredicate) {
        this.cartPredicate = cartPredicate.orElse(null);
        return this;
    }

    public DiscountCodeDraftBuilder cartPredicate(final CartDiscountPredicate cartPredicate) {
        return cartPredicate(Optional.of(cartPredicate));
    }

    public DiscountCodeDraftBuilder isActive(final boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public DiscountCodeDraftBuilder maxApplications(final Optional<Long> maxApplications) {
        this.maxApplications = maxApplications.orElse(null);
        return this;
    }

    public DiscountCodeDraftBuilder maxApplications(final long maxApplications) {
        return maxApplications(Optional.of(maxApplications));
    }

    public DiscountCodeDraftBuilder maxApplicationsPerCustomer(final Optional<Long> maxApplicationsPerCustomer) {
        this.maxApplicationsPerCustomer = maxApplicationsPerCustomer.orElse(null);
        return this;
    }

    public DiscountCodeDraftBuilder maxApplicationsPerCustomer(final long maxApplicationsPerCustomer) {
        return maxApplicationsPerCustomer(Optional.of(maxApplicationsPerCustomer));
    }

    @Override
    public DiscountCodeDraft build() {
        return new DiscountCodeDraft(cartDiscounts, name, description, code, Optional.ofNullable(cartPredicate).map(CartDiscountPredicate::toSphereCartDiscountPredicate).orElse(null), isActive, maxApplications, maxApplicationsPerCustomer);
    }
}
