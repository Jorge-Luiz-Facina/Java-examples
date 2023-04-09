package org.example.store.controller;

import org.example.store.client.StatisticGeneralClient;
import org.example.store.client.dto.StatisticClientResponseDTO;
import org.example.store.messenger.producer.StatisticReportEmailProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/statistic-general")
public class StatisticGeneralController {

    private final StatisticGeneralClient statisticGeneralClient;
    private final StatisticReportEmailProducer statisticReportEmailProducer;

    public StatisticGeneralController(StatisticGeneralClient statisticGeneralClient, StatisticReportEmailProducer statisticReportEmailProducer) {
        this.statisticGeneralClient = statisticGeneralClient;
        this.statisticReportEmailProducer = statisticReportEmailProducer;
    }

    @GetMapping("/one")
    public ResponseEntity<StatisticClientResponseDTO> getOne() {
        return statisticGeneralClient.getOne();
    }

    @GetMapping("/two")
    public ResponseEntity<StatisticClientResponseDTO> getTwo() {
        return statisticGeneralClient.getTwo();
    }

    @GetMapping("/report-email")
    public ResponseEntity<Void> getReportEmail() {
        statisticReportEmailProducer.send();
        return ResponseEntity.ok().build();
    }
}
