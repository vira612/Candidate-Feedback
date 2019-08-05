package data;


import java.util.ArrayList;
import java.util.List;

public class Candidates {
	
	Integer id;
	
	String name;
	
	String speciality;
	
	String presentation;
	
	List<Feedback> feedback;
	
	String avg_rate;
	
	
	public Candidates() 
	{
		
	}
	

	public Candidates( Integer id, String name, String speciality, String avg_rate,String presentation)
    {
        this.id = id;
        this.name = name;
        this.speciality = speciality;
        this.presentation = presentation;
        this.avg_rate=avg_rate;
        feedback = new ArrayList<Feedback>();

    }
	
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getPresentation() {
		return presentation;
	}

	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}
	
	 public List<Feedback> getFeedback() {
			return feedback;
		}

		public void setFeedback(List<Feedback> feedback) {
			this.feedback = feedback;
		}
		
		public String getAvg_rate() {
			return avg_rate;
		}




		public void setAvg_rate(String avg_rate) {
			this.avg_rate = avg_rate;
		}

	 
	 
	
	

}