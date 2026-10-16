package com.anne.mvc.service.impl;

import com.anne.mvc.entity.Device;
import com.anne.mvc.repository.DeviceRepository;
import com.anne.mvc.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    DeviceRepository repo;

    @Override
    public List<Device> getDevices() {
        return repo.findAllByOrderByInstallationDateDesc();
    }
}
