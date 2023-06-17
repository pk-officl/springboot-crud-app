package in.pk.springcrudapp.services;

import in.pk.springcrudapp.entities.User;

public interface UserService {

	public User saveUser(User user);

	public User findUserById(Long id);

	public Iterable<User> findAllUsers();

	public void deleteUser(User user);

	public User findUserByEmail(String email);

	public String generateToken(User user);
}