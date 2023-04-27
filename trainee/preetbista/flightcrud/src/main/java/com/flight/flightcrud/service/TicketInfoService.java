package com.flight.flightcrud.service;


import com.flight.flightcrud.dto.TicketInfo;

import java.util.List;

public interface TicketInfoService {
    TicketInfo findTicketInfoByPassengerId(Long id);
}
