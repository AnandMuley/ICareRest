package abm.icare.dtos;

import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PatientQueueDto {

	private String id;
	private Set<AppointmentDto> live = new TreeSet<AppointmentDto>();
	private Set<AppointmentDto> onhold = new TreeSet<AppointmentDto>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Set<AppointmentDto> getLive() {
		return live;
	}

	public Set<AppointmentDto> getOnhold() {
		return onhold;
	}
}
