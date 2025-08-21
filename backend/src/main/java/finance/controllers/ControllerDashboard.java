package finance.controllers;

import finance.dto.dashboard.DashboardDTO;
import finance.services.ServiceDashboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard")
public class ControllerDashboard {
    
    @Autowired
    private ServiceDashboard serviceDashboard;
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<DashboardDTO> getDashboard(@PathVariable Long userId) {
        var dashboard = serviceDashboard.getDashboard(userId);
        return ResponseEntity.ok(dashboard);
    }
}