package daos;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.Token;
import entities.User;

public interface TokenDao extends JpaRepository<Token, Integer> {

    Token findByUser(User user);
}
