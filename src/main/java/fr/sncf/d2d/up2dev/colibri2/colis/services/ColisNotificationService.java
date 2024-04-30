package fr.sncf.d2d.up2dev.colibri2.colis.services;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import fr.sncf.d2d.up2dev.colibri2.colis.models.Colis;

@Service
public class ColisNotificationService {
    
    private static final String PENDING_SUBJECT = "Votre colis est en attente.";
    private static final String PENDING_TEMPLATE = "Votre colis à destination de %s est en attente.";

    private final MailSender mailSender;

    public ColisNotificationService(MailSender sender){
        this.mailSender = sender;
    }

    public void notifyRecipient(Colis colis){
        final var message = new SimpleMailMessage();
        message.setSubject(PENDING_SUBJECT);
        message.setText(String.format(PENDING_TEMPLATE, colis.getAddress()));
        message.setTo(colis.getEmail());
        message.setFrom("noreply@colibri.com");
        this.mailSender.send(message);
    }
}
