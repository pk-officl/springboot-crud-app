package in.pk.springcrudapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import in.pk.springcrudapp.entities.User;

public interface UserRepo extends JpaRepository<User,Long>{
	public User findByEmail(String email);
}
