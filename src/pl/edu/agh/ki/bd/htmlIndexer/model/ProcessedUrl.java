package pl.edu.agh.ki.bd.htmlIndexer.model;

import java.util.Date;
import java.util.List;

public class ProcessedUrl {
	
	private long id;
	private String url;
	private Date date;
	private List<Sentence> sentenceList;

	public ProcessedUrl(){

	}

	public ProcessedUrl(String url, Date date) {
		this.url = url;
		this.date=date;
	}

	public ProcessedUrl(String url, Date date, List<Sentence> sentenceList){
		this.sentenceList=sentenceList;
		this.setDate(date);
		this.setUrl(url);
	}

	public List<Sentence> getSentenceList() {
		return sentenceList;
	}

	public void setSentenceList(List<Sentence> sentenceList) {
		this.sentenceList = sentenceList;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
}
