package test.action;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class XmlAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// 요청할 경로를 변수에 저장한다.
		String path = "http://localhost:8080/Ex20240612_goData/data/test.xml";
		
		URL url = null;
		try {
			url = new URL(path); // 웹 상의 경로를 객체화한 것
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestProperty("Content-Type", "application/xml");
			// 헤더에 받을 자원이 어떤 자원인지? mimeType을 지정한다.
			conn.connect();
			
			// 이제 요청하여 받을 자우너들을 처리할 JDOM객체들을 준비
			SAXBuilder builder = new SAXBuilder();
			
			// 문서객체 생성
			Document doc = builder.build(conn.getInputStream());
			
			// 생성된 문서객체로부터 루트를 얻어내자
			Element root = doc.getRootElement();
			System.out.println(root.getName());
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

}
