package fr.sncf.d2d.up2dev.colibri2.users.configuration;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import fr.sncf.d2d.up2dev.colibri2.users.models.User;

@Configuration
@ConfigurationProperties(prefix = "colibri")
public class UsersConfigurationProperties {
    
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
