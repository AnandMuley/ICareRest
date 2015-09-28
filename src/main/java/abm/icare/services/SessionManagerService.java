package abm.icare.services;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

@Service
public class SessionManagerService {

	public byte[] encode(String password) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		return digest.digest(password.getBytes("utf-8"));
	}
}
