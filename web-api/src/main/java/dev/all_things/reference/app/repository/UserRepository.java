package dev.all_things.reference.app.repository;

import java.util.List;

import dev.all_things.reference.app.model.user.User;
import dev.all_things.reference.common.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserRepository
		extends BaseRepository<User, Long>
{
	private final UserDataRepository repository;

	@Autowired
	public UserRepository(final UserDataRepository repository)
	{
		super(User.class);
		this.repository = repository;
	}

	@Transactional
	public void createAll(final List<User> users)
	{
		this.repository.saveAll(users);
	}
}
