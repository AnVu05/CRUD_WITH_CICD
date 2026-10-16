package com.anne.mvc.service;

import com.anne.mvc.entity.Category;
import com.anne.mvc.entity.Device;
import com.anne.mvc.repository.DeviceRepository;
import com.anne.mvc.service.impl.DeviceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeviceServiceTest {

    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    private DeviceServiceImpl deviceService;

    private Device device1;
    private Device device2;

    @BeforeEach
    void setUp() {
        Category category = Category.builder()
                .id(1)
                .name("Smart Home")
                .build();

        device1 = Device.builder()
                .id(1)
                .code("DEV001")
                .name("Smart Sensor A")
                .cate(category)
                .price(150.0)
                .warrantyMonths(12)
                .installationDate(LocalDate.of(2026, 5, 20))
                .status("Active")
                .build();

        device2 = Device.builder()
                .id(2)
                .code("DEV002")
                .name("Smart Light B")
                .cate(category)
                .price(80.0)
                .warrantyMonths(6)
                .installationDate(LocalDate.of(2026, 1, 10))
                .status("Inactive")
                .build();
    }

    @Test
    @DisplayName("Nghiệp vụ: Lấy tất cả thiết bị sắp xếp giảm dần theo ngày lắp đặt (Installation Date Descending)")
    void getDevices_shouldReturnDevicesOrderedByInstallationDateDesc() {
        // Given: Giả lập repository trả về danh sách đã sắp xếp giảm dần theo installationDate
        when(deviceRepository.findAllByOrderByInstallationDateDesc())
                .thenReturn(List.of(device1, device2));

        // When: Gọi service.getDevices()
        List<Device> result = deviceService.getDevices();

        // Then: Kiểm tra kết quả
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getInstallationDate())
                .isAfter(result.get(1).getInstallationDate());
        assertThat(result.get(0).getCode()).isEqualTo("DEV001");
        assertThat(result.get(1).getCode()).isEqualTo("DEV002");

        // Xác nhận repository được gọi đúng hàm
        verify(deviceRepository).findAllByOrderByInstallationDateDesc();
    }

    @Test
    @DisplayName("Nghiệp vụ: Khi database không có bản ghi nào, trả về danh sách rỗng")
    void getDevices_shouldReturnEmptyList_whenNoDevicesInDatabase() {
        // Given
        when(deviceRepository.findAllByOrderByInstallationDateDesc())
                .thenReturn(Collections.emptyList());

        // When
        List<Device> result = deviceService.getDevices();

        // Then
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();

        verify(deviceRepository).findAllByOrderByInstallationDateDesc();
    }
}
