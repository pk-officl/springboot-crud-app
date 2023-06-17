package in.pk.springcrudapp.services;

import java.util.Date;

import javax.transaction.Transactional;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.pk.springcrudapp.constants.SecurityConstants;
import in.pk.springcrudapp.entities.User;
import in.pk.springcrudapp.repositories.UserRepo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
    private UserRepo userRepo;
	
	@Override
	public User saveUser(User user) {
        return userRepo.save(user);
    }

	@Override
    public User findUserById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

	@Override
    public Iterable<User> findAllUsers() {
        return userRepo.findAll();
    }

	@Override
    public void deleteUser(User user) {
        userRepo.delete(user);
    }
    
	@Override
    public User findUserByEmail(String email) {
    	return userRepo.findByEmail(email);
    }

	@Override
	public String generateToken(User user) {
		JSONObject resJson = new JSONObject();
		try {
			long timeStamp = System.currentTimeMillis();
			String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, SecurityConstants.SECRET_KEY)
					.setIssuedAt(new Date(timeStamp))
					.setExpiration(new Date(timeStamp + (SecurityConstants.TOKEN_VALIDITY)))
					.claim("userEmail", user.getEmail()).compact();
			resJson.put("authToken", token);
			return token;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
