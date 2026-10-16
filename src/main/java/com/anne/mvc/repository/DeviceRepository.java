package com.anne.mvc.repository;

import com.anne.mvc.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {
    public List<Device> findAllByOrderByInstallationDateDesc();
}
