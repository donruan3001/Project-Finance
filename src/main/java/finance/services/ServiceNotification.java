package finance.services;

import finance.domain.notifications.Notification;
import finance.domain.notifications.NotificationType;
import finance.domain.user.User;
import finance.repository.RepositoryNotification;
import finance.repository.RepositoryUser;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServiceNotification {
    
    @Autowired
    private RepositoryNotification repositoryNotification;
    
    @Autowired
    private RepositoryUser repositoryUser;
    
    @Transactional
    public void createNotification(Long userId, String title, String message, 
                                 NotificationType type, String actionUrl) {
        var user = repositoryUser.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        
        var notification = new Notification(user, title, message, type, actionUrl);
        repositoryNotification.save(notification);
    }
    
    public List<Notification> getUnreadNotifications(Long userId) {
        var user = repositoryUser.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        
        return repositoryNotification.findByUserAndIsReadFalseOrderByCreatedAtDesc(user);
    }
    
    public List<Notification> getAllNotifications(Long userId) {
        var user = repositoryUser.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        
        return repositoryNotification.findByUserOrderByCreatedAtDesc(user);
    }
    
    @Transactional
    public void markAsRead(Long notificationId) {
        var notification = repositoryNotification.findById(notificationId)
            .orElseThrow(() -> new IllegalArgumentException("Notificação não encontrada"));
        
        notification.setIsRead(true);
        notification.setReadAt(LocalDateTime.now());
        repositoryNotification.save(notification);
    }
    
    @Transactional
    public void markAllAsRead(Long userId) {
        var user = repositoryUser.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        
        var unreadNotifications = repositoryNotification.findByUserAndIsReadFalseOrderByCreatedAtDesc(user);
        
        unreadNotifications.forEach(notification -> {
            notification.setIsRead(true);
            notification.setReadAt(LocalDateTime.now());
        });
        
        repositoryNotification.saveAll(unreadNotifications);
    }
    
    public Long getUnreadCount(Long userId) {
        var user = repositoryUser.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        
        return repositoryNotification.countUnreadNotifications(user);
    }
}