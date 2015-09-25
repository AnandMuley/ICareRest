package abm.icare.dtos;

import java.util.PriorityQueue;
import java.util.Queue;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PatientQueueDto {

	private String id;
	private Queue<AppointmentDto> live = new PriorityQueue<AppointmentDto>();
	private Queue<AppointmentDto> onhold = new PriorityQueue<AppointmentDto>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Queue<AppointmentDto> getLive() {
		return live;
	}

	public Queue<AppointmentDto> getOnhold() {
		return onhold;
	}
}
