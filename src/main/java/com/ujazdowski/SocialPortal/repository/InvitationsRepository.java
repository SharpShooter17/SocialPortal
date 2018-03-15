package com.ujazdowski.SocialPortal.repository;

import com.ujazdowski.SocialPortal.model.tables.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface InvitationsRepository extends JpaRepository<Invitation, Long> {
    @Query(value = "SELECT i FROM Invitation i WHERE (i.fromUser.userId = :uf AND i.toUser.userId = :us) OR (i.fromUser.userId = :us AND i.toUser.userId = :uf)")
    List<Invitation> usersAreFriends(@Param("uf") Long user1, @Param("us")Long user2);

    @Query(value="SELECT i FROM Invitation i WHERE (i.toUser.userId = :u OR i.fromUser.userId = :u) AND i.accepted = true")
    List<Invitation> findAllFriends(@Param("u") Long userId);
}
