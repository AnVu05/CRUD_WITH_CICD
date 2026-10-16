package com.anne.mvc.controller;

import com.anne.mvc.entity.Category;
import com.anne.mvc.entity.Device;
import com.anne.mvc.service.DeviceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class DeviceControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DeviceService deviceService;

    @InjectMocks
    private DeviceController deviceController;

    private Device device1;
    private Device device2;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(deviceController).build();

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
    @DisplayName("Nghiệp vụ: Khi người dùng truy cập trang, trả về view 'list' cùng danh sách thiết bị trong model")
    void getDevices_shouldReturnListViewAndDeviceList_whenDevicesExist() throws Exception {
        // Given: Giả lập service trả về danh sách các thiết bị đã sắp xếp
        when(deviceService.getDevices()).thenReturn(List.of(device1, device2));

        // When & Then: Gửi request GET /
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("list"))
                .andExpect(model().attributeExists("devices"))
                .andExpect(model().attribute("devices", hasSize(2)))
                .andExpect(model().attribute("devices", List.of(device1, device2)));

        verify(deviceService).getDevices();
    }

    @Test
    @DisplayName("Nghiệp vụ: Khi không có dữ liệu thiết bị, model chứa danh sách rỗng (size = 0)")
    void getDevices_shouldReturnEmptyListInModel_whenNoDevicesExist() throws Exception {
        // Given: Giả lập service trả về danh sách rỗng
        when(deviceService.getDevices()).thenReturn(Collections.emptyList());

        // When & Then: Gửi request GET /
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("list"))
                .andExpect(model().attributeExists("devices"))
                .andExpect(model().attribute("devices", hasSize(0)));

        verify(deviceService).getDevices();
    }
}
