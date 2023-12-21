package dev.all_things.reference.app.service;

import java.util.*;

import dev.all_things.reference.app.model.user.User;
import dev.all_things.reference.app.model.user.UserStatus;
import dev.all_things.reference.app.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService
{
	private static final Logger logger = LogManager.getLogger(UserService.class);

	private final UserRepository repository;

	@Autowired
	public UserService(final UserRepository repository)
	{
		this.repository = repository;
	}

	@Transactional
	public void initialize()
	{
		final List<User> users = new ArrayList<>();

		for (int i = 0; i < 100; i++)
		{
			final User user = new User();

			user.setUid(UUID.randomUUID());
			user.setFirstName("Thomas");
			user.setLastName("Anderson");
			user.setDisplayName("Neo");
			user.setEmail("a@b.com");
			user.setContactNumber("a@b.com");
			user.setStatus(UserStatus.CREATED);
			user.setCreatedBy(1L);
			user.setUpdatedBy(1L);
			user.setRoleId(1L);

			users.add(user);
		}

		this.repository.createAll(users);
	}
}
