package fr.sncf.d2d.up2dev.colibri2.colis.services;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import fr.sncf.d2d.up2dev.colibri2.colis.models.Colis;
import fr.sncf.d2d.up2dev.colibri2.colis.models.ColisStatus;

@Service
public class ColisNotificationService {
    
    private static final String PENDING_SUBJECT = "Votre colis est en attente.";
    private static final String PENDING_TEMPLATE = "Votre colis à destination de %s est en attente.";

    private static final String IN_TRANSIT_SUBJECT = "Votre colis est en cours de livraison";
    private static final String IN_TRANSIT_TEMPLATE = "Votre colis à destination de %s est en cours de livraison";

    private static final String DELIVERED_SUBJECT = "Votre colis a été livré !";
    private static final String DELIVERED_TEMPLATE = "Votre colis à destination de %s a été livré.";

    private static final String RETURNED_SUBJECT = "Votre colis a été retourné.";
    private static final String RETURNED_TEMPLATE = "Votre colis à destination de %s a été retourné à l'expéditeur.";

    private final MailSender mailSender;

    public ColisNotificationService(MailSender sender){
        this.mailSender = sender;
    }

    public void notifyRecipient(Colis colis){
        final var message = new SimpleMailMessage();
        message.setTo(colis.getEmail());
        message.setFrom("noreply@colibri.com");
        message.setSubject(this.getNotificationSubject(colis.getStatus()));
        message.setText(this.getNotificationMesssage(colis));
        this.mailSender.send(message);
    }

    private String getNotificationSubject(ColisStatus status){
        return switch (status){
            case PENDING -> PENDING_SUBJECT;
            case IN_TRANSIT -> IN_TRANSIT_SUBJECT;
            case DELIVERED -> DELIVERED_SUBJECT;
            case RETURNED -> RETURNED_SUBJECT;
        };
    }

    private String getNotificationMesssage(Colis colis){
        return switch (colis.getStatus()){
            case PENDING -> String.format(PENDING_TEMPLATE, colis.getAddress());
            case IN_TRANSIT -> String.format(IN_TRANSIT_TEMPLATE, colis.getAddress());
            case DELIVERED -> String.format(DELIVERED_TEMPLATE, colis.getAddress());
            case RETURNED -> String.format(RETURNED_TEMPLATE, colis.getAddress());
        };
    }
}
