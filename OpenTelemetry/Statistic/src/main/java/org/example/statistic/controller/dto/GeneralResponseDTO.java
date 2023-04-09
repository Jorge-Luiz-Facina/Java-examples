package org.example.statistic.controller.dto;

import java.math.BigDecimal;

public class GeneralResponseDTO {

    private BigDecimal invoice = BigDecimal.TEN;

    public BigDecimal getInvoice() {
        return invoice;
    }
}
