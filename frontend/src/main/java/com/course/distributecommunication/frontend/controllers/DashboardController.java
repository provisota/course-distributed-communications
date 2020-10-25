package com.course.distributecommunication.frontend.controllers;

import com.course.distributecommunication.frontend.models.Aggregate;
import com.course.distributecommunication.frontend.services.DashboardGrpcService;
import com.course.distributecommunication.frontend.services.DashboardRestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dashboard")
public class DashboardController {
    private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);

    final DashboardRestService dashboardRestService;
    final DashboardGrpcService dashboardGrpcService;

    public DashboardController(
            DashboardRestService dashboardRestService,
            DashboardGrpcService dashboardGrpcService
    ) {
        this.dashboardRestService = dashboardRestService;
        this.dashboardGrpcService = dashboardGrpcService;
    }

    @GetMapping("/rest")
    public ResponseEntity<Aggregate> getAllRest() {
        return new ResponseEntity<>(dashboardRestService.getAggregate(), HttpStatus.OK);
    }

    @GetMapping("/grpc")
    public ResponseEntity<Aggregate> getAllGrpc() {
        return new ResponseEntity<>(dashboardGrpcService.getAggregate(), HttpStatus.OK);
    }
}
