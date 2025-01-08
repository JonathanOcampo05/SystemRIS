package com.example.RIS.utils;

import com.example.RIS.ManejoTurnos.control.TurnosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CronJobs {

    private static final Logger logger = LoggerFactory.getLogger(CronJobs.class);
    private final TurnosService turnosService;

    public CronJobs(TurnosService turnosService) {
        this.turnosService = turnosService;
    }

    //Método que se ejecuta cada minuto
    //@Scheduled(cron = "0 *5 * * * ?")
    /*public void imprimirMarcaModelo() {
        Bus bus = busService.changeStatus();
        if (bus != null) {
            logger.info(bus.getMarca());
            logger.info(bus.getModelo());
        } else {
            logger.info("No hay buses");
        }
    }*/
}
