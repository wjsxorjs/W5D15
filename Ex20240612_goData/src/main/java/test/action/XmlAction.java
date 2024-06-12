package test.action;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import test.vo.PersonalVO;

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
//			System.out.println(root.getName());
			
			Element totalCount = root.getChild("totalCount");
			List<Element> p_list = root.getChildren("personal");
			// root.getChildren(); // 자식 모두(personal, totalCount)
			
			PersonalVO[] p_ar = new PersonalVO[p_list.size()];
			// 위의 배열이 지금은 비어있지만 아래의 반복문을 수행하면
			// 채워지도록 해야하고 이 배열이 request에 저장되어야한다.
			int idx = 0;
			
			for(Element personal: p_list) {
				// List<Element> children = e.getChildren();
				// Element name = e.getChild("name");
				// 이름 - personal안에 있는 자식들 중
				//		 태그이름이 "name"인 요소의 문자열 가져오기
				String name = personal.getChildText("name");
				String email = personal.getChildText("email");
				
//				System.out.printf("이름: %s || 이메일: %s\r\n",name,email);
				
				PersonalVO pvo = new PersonalVO(name, email);
				
				p_ar[idx] = pvo;
				idx++;
			} // for의 끝
			
			request.setAttribute("p_ar", p_ar);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "/jsp/personal.jsp";
	}

}
