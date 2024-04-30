package fr.sncf.d2d.up2dev.colibri2.users.configuration;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import fr.sncf.d2d.up2dev.colibri2.users.models.User;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Configuration
@ConfigurationProperties(prefix = "colibri")
@Validated
public class UsersConfigurationProperties {
    
    @NotNull
    @Size(min = 1, message = "il doit y avoir au moins un utilisateur")
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
