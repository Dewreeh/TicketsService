package org.repin.service;

import org.repin.dto.CarrierDto;
import org.repin.model.Carrier;
import org.repin.repository.CarrierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarrierService {

    private final CarrierRepository carrierRepository;

    @Autowired
    public CarrierService(CarrierRepository carrierRepository) {
        this.carrierRepository = carrierRepository;
    }

    public void addCarrier(CarrierDto carrierDto) {
        Carrier carrier = new Carrier();
        carrier.setName(carrierDto.getName());
        carrier.setPhone(carrierDto.getPhone());

        carrierRepository.save(carrier);
    }

    public void updateCarrier(Carrier carrier) {
        carrierRepository.update(carrier);
    }

    public void deleteCarrier(Long id) {
        carrierRepository.delete(id);
    }
}