package finance.controllers;

import finance.dto.analytics.AnalyticsDTO;
import finance.services.ServiceAnalytics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/analytics")
public class ControllerAnalytics {
    
    @Autowired
    private ServiceAnalytics serviceAnalytics;
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<AnalyticsDTO> getAnalytics(
            @PathVariable Long userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        
        var analytics = serviceAnalytics.getAnalytics(userId, startDate, endDate);
        return ResponseEntity.ok(analytics);
    }
    
    @GetMapping("/user/{userId}/current-month")
    public ResponseEntity<AnalyticsDTO> getCurrentMonthAnalytics(@PathVariable Long userId) {
        LocalDate startDate = LocalDate.now().withDayOfMonth(1);
        LocalDate endDate = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
        
        var analytics = serviceAnalytics.getAnalytics(userId, startDate, endDate);
        return ResponseEntity.ok(analytics);
    }
}