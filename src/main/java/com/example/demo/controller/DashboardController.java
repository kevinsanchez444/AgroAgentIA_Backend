package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.DashboardResponse;
import com.example.demo.service.DashboardService;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping
    public DashboardResponse obtenerDashboard() {

        return dashboardService.obtenerDashboard();

    }

}