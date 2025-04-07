package org.repin.service;

import org.repin.dto.RouteDto;
import org.repin.model.Route;
import org.repin.repository.CarrierRepository;
import org.repin.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteService {

    private final RouteRepository routeRepository;
    private final CarrierRepository carrierRepository;

    @Autowired
    public RouteService(RouteRepository routeRepository,
                        CarrierRepository carrierRepository) {
        this.routeRepository = routeRepository;
        this.carrierRepository = carrierRepository;
    }

    public void addRoute(RouteDto routeDto) {
        Route route = new Route();
        route.setDeparturePoint(routeDto.getDeparturePoint());
        route.setDestinationPoint(routeDto.getDestinationPoint());
        route.setCarrier_id(routeDto.getCarrierId());
        route.setDurationMinutes(routeDto.getDurationMinutes());

        routeRepository.save(route);
    }

    public void updateRoute(Route route) {
        routeRepository.update(route);
    }

    public void deleteRoute(Long id) {
        routeRepository.delete(id);
    }

}