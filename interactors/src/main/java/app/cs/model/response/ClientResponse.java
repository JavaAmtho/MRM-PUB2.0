package app.cs.model.response;

/**
 * The Class CustomResponse.
 */
public class ClientResponse implements ResponseModel {

	/** The html. */
	private String html;

	/** The events. */
	private String events;

	/** The elements. */
	private String elements;

	/**
	 * Instantiates a new custom response.
	 */
	public ClientResponse() {

	}

	/**
	 * Instantiates a new custom response.
	 * 
	 * @param html
	 *            the html
	 * @param events
	 *            the events
	 * @param elements
	 *            the elements
	 */
	public ClientResponse(String html, String events, String elements) {
		super();
		this.html = html;
		this.events = events;
		this.elements = elements;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cs.data.business.model.ICustomResponse#getHtml()
	 */
	public String getHtml() {
		return html;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cs.data.business.model.ICustomResponse#setHtml(java.lang.String)
	 */
	public void setHtml(String html) {
		this.html = html;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cs.data.business.model.ICustomResponse#getEvents()
	 */
	public String getEvents() {
		return events;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cs.data.business.model.ICustomResponse#setEvents(java.lang.String)
	 */
	public void setEvents(String events) {
		this.events = events;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cs.data.business.model.ICustomResponse#getElements()
	 */
	public String getElements() {
		return elements;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cs.data.business.model.ICustomResponse#setElements(java.lang.String)
	 */
	public void setElements(String elemnts) {
		this.elements = elemnts;
	}

}
