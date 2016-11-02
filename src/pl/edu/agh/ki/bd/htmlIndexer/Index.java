package pl.edu.agh.ki.bd.htmlIndexer;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import pl.edu.agh.ki.bd.htmlIndexer.model.ProcessedUrl;
import pl.edu.agh.ki.bd.htmlIndexer.model.Sentence;
import pl.edu.agh.ki.bd.htmlIndexer.persistence.HibernateUtils;

public class Index 
{
	public void indexWebPage(String url) throws IOException
	{
		Document doc = Jsoup.connect(url).get();
		Elements elements = doc.body().select("*");

		ProcessedUrl processedUrl = new ProcessedUrl(url, new Date());
		List<Sentence> sentenceList = new LinkedList<>();
		
		for (Element element : elements) 
		{
			if (element.ownText().trim().length() > 1)
			{
				for (String sentenceContent : element.ownText().split("\\. "))
				{
					

					Sentence sentence = new Sentence(sentenceContent, processedUrl);

					sentenceList.add(sentence);
				
				}
			}
		}

		processedUrl.setSentenceList(sentenceList);

		Session session = HibernateUtils.getSession();
		Transaction transaction = session.beginTransaction();

		session.persist(processedUrl);

		transaction.commit();
		session.close();	
	}	
	
	public List<Sentence> findSentencesByWords(String words)
	{
		Session session = HibernateUtils.getSession();
		Transaction transaction = session.beginTransaction();
		
		String query = "%" + words.replace(" ", "%") + "%";
		List<Sentence> result = session.createQuery("select s from Sentence s where s.content like :query", Sentence.class).setParameter("query", query).getResultList();
		
		transaction.commit();
		session.close();
		
		return result;
	}	
	
	public List<String> findSentencesByLength(int len)
	{
		Session session = HibernateUtils.getSession();
		Transaction transaction = session.beginTransaction();

		List<String> result = session.createQuery("select s.content from Sentence s where length(s.content) > :len", String.class).setParameter("len", len).getResultList();

		transaction.commit();
		session.close();
		
		return result;
	}

	public void printIndexedWebsites(){
		Session session = HibernateUtils.getSession();
		Transaction transaction = session.beginTransaction();

		List<Object[]> result = session.createQuery("select p.url, p.sentenceList.size as s from ProcessedUrl  p order by s desc").getResultList();

		for(Object[] sentence: result){
			System.out.println("url " + sentence[0] + " sentences number " + sentence[1]);
		}

		transaction.commit();
		session.close();
	}
	
	
}
