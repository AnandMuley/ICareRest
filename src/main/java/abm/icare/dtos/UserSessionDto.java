package abm.icare.dtos;

import org.springframework.stereotype.Component;

@Component
public class UserSessionDto {

	private byte[] authcode;

	public byte[] getAuthcode() {
		return authcode;
	}

	public void setAuthcode(byte[] authcode) {
		this.authcode = authcode;
	}

}
