package data;

import java.util.Date;

public class Feedback {
	

	Integer rate;
	
	String name_f;
	
	String comment;
	
	Date date;
	
	Integer owner_id;
	
	public Integer getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(Integer owner_id) {
		this.owner_id = owner_id;
	}

	public Feedback(Integer rate,String name_f,String comment,Date date){
		
		this.rate=rate;
		this.name_f=name_f;
		this.comment=comment;
		this.date=date;
		this.owner_id=owner_id;
		
	}
	
		public Feedback(Integer rate,String name_f,String comment,Date date,int owner_id){
		
		this.rate=rate;
		this.name_f=name_f;
		this.comment=comment;
		this.date=date;
		this.owner_id=owner_id;
		
	}
	
	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public String getName_f() {
		return name_f;
	}

	public void setName_f(String name_f) {
		this.name_f = name_f;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	

}