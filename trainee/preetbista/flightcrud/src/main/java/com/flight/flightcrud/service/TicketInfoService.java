package com.flight.flightcrud.service;

import com.flight.flightcrud.dto.TicketInfo;

public interface TicketInfoService {
    TicketInfo findTicketInfoByPassengerId(Long id);
}
