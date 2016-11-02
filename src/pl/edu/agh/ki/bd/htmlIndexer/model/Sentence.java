package pl.edu.agh.ki.bd.htmlIndexer.model;

import org.hibernate.annotations.CascadeType;
import pl.edu.agh.ki.bd.htmlIndexer.model.ProcessedUrl;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


public class Sentence {
	
	private long id;
	private String content;

	public ProcessedUrl getProcessedUrl() {
		return processedUrl;
	}

	public void setProcessedUrl(ProcessedUrl processedUrl) {
		this.processedUrl = processedUrl;
	}

	private ProcessedUrl processedUrl;
	
	public Sentence() 
	{
	}

	public Sentence(String content, ProcessedUrl  processedUrl){
		this.setContent(content);
		this.setProcessedUrl(processedUrl);
	}

	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
