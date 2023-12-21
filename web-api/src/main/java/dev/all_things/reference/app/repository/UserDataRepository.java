package dev.all_things.reference.app.repository;

import dev.all_things.reference.app.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataRepository
		extends JpaRepository<User, Long>
{

}
