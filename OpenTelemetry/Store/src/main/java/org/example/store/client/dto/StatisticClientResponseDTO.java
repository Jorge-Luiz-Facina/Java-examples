package org.example.store.client.dto;

import java.math.BigDecimal;

public class StatisticClientResponseDTO {
    private BigDecimal invoice = BigDecimal.TEN;

    public BigDecimal getInvoice() {
        return invoice;
    }
}
