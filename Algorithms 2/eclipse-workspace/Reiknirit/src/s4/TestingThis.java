package s4;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import edu.princeton.cs.algs4.StdOut;


public class TestingThis {

	public static void main(String[] args) throws IOException {
		Document doc = Jsoup.connect("http://en.wikipedia.org/").get();
		Elements newsHeadlines = doc.select("#mp-itn b a");
		
		for(Element src : newsHeadlines) {
			StdOut.println(src);
		}
	}

}
