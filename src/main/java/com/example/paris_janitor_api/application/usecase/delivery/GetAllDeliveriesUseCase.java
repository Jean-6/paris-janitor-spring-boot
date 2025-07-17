package com.example.paris_janitor_api.application.usecase.delivery;


import com.example.paris_janitor_api.application.port.in.delivery.LoadAllDeliveriesPort;
import com.example.paris_janitor_api.application.port.out.delivery.LoadDeliveriesPort;
import com.example.paris_janitor_api.core.model.Delivery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllDeliveriesUseCase implements LoadAllDeliveriesPort {

    private final LoadDeliveriesPort loadDeliveriesPort;

    public GetAllDeliveriesUseCase(LoadDeliveriesPort loadDeliveriesPort) {
        this.loadDeliveriesPort = loadDeliveriesPort;
    }


    @Override
    public List<Delivery> getAllDeliveries() {
        return loadDeliveriesPort.findAll();
    }

}
