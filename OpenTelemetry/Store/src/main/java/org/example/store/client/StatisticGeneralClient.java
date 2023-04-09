package org.example.store.client;

import org.example.store.client.dto.StatisticClientResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "statistic-general", url = "${application.url.statistic}/general")
public interface StatisticGeneralClient {

    @RequestMapping(method = RequestMethod.GET, value = "/one")
    ResponseEntity<StatisticClientResponseDTO> getOne();

    @RequestMapping(method = RequestMethod.GET, value = "/two")
    ResponseEntity<StatisticClientResponseDTO> getTwo();
}