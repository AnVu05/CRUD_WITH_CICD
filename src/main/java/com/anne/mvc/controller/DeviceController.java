package com.anne.mvc.controller;

import com.anne.mvc.entity.Device;
import com.anne.mvc.service.DeviceService;
import org.hibernate.annotations.Audited;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("https://crud-with-cicd.onrender.com/")
public class DeviceController {
    @Autowired
    DeviceService service;

    @GetMapping
    public String getDevices(Model box) {
        box.addAttribute("devices", service.getDevices());
        return "list";
    }
}
