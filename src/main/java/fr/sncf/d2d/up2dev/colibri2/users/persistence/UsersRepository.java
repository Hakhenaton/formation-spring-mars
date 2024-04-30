package fr.sncf.d2d.up2dev.colibri2.users.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import fr.sncf.d2d.up2dev.colibri2.users.configuration.UsersConfigurationProperties;
import fr.sncf.d2d.up2dev.colibri2.users.models.User;

@Repository
public class UsersRepository {
    
    private final List<User> users;

    public UsersRepository(UsersConfigurationProperties usersConfigurationProperties){
        this.users = usersConfigurationProperties.getUsers();
    }

    public Optional<User> findByUsername(String username){
        /*for (final var user: users){
            if (user.getUsername().equals(username)){
                return Optional.of(user);
            }
        }
        return Optional.empty();*/
        return this.users.stream()
            .filter(user -> user.getUsername().equals(username))
            .findFirst();
    }
}
