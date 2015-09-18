package abm.icare.config;

public interface RootMockConfig {

	String PATIENT_FIND_BY_NAME = "patient/findbyname";
	String PATIENT_CREATE = "patient/create";
	String PATIENT_UPDATE = "patient/update";

	String MEDICINE_SEARCH_BY_NAME = "medicine/searchbyname";
	String VISIT_CREATE = "visit/create";
	String VISIT_UPDATE = "visit/update";
	String VISIT_FIND_ALL = "visit/findall";

	String APPOINTMENT_CREATE = "appointment/create";
	String APPOINTMENT_FETCH_ALL = "appointment/findby";

	String PATIENT_QUEUE_PUT_ONHOLD = "queue/putonhold";
	String PATIENT_QUEUE_MOVE_TO_LIVE = "queue/movetolive";
}
