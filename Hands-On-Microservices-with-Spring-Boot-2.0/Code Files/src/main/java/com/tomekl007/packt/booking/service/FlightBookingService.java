package com.tomekl007.packt.booking.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tomekl007.packt.booking.api.BookingService;
import com.tomekl007.packt.booking.domain.Travel;
import com.tomekl007.packt.booking.infrastructure.configuration.BookingServiceSettings;
import com.tomekl007.packt.booking.infrastructure.persistance.TravelRepository;
import com.tomekl007.packt.eventbus.api.EventBus;
import com.tomekl007.packt.eventbus.domain.Event;

@Component
public class FlightBookingService implements BookingService {
	private static final Logger log = LoggerFactory.getLogger(FlightBookingService.class.getName());

    private final BookingServiceSettings bookingServiceSettings;
    private final TravelRepository travelRepository;
    private EventBus eventBus;

    @Autowired
    public FlightBookingService(BookingServiceSettings bookingServiceSettings,
                                TravelRepository travelRepository,
                                EventBus eventBus) {
        this.bookingServiceSettings = bookingServiceSettings;
        this.travelRepository = travelRepository;
        this.eventBus = eventBus;
    }

    @Override
    public boolean book(Travel travel) {
        if (bookingServiceSettings.getSupportedDestinations().contains(travel.getDestination())) {
            travelRepository.save(travel);
            eventBus.publish(new Event("SAVED", "Saved travel " + travel));
            return true;
        }
        eventBus.publish(new Event("REJECTED", "Rejected travel " + travel));
        return false;
    }

    @PostConstruct
    public void init() {
        log.info("in init method");
    }

    @PreDestroy
    public void cleanup() {
        log.info("in cleanup method. Possible to release some resources");
    }
}
