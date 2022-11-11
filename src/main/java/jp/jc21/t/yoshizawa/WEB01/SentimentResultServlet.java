package jp.jc21.t.yoshizawa.WEB01;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/resultSentiment")
public class SentimentResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SentimentResultServlet() {
		super();
	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String string = "生姜焼定食";
		
		try {
			Language1 result = Sentiment.getLanguage(string);
			String message = result.documents[0].confidenceScores.positive;
			String message2 = result.documents[0].confidenceScores.neutral;
			String message3 = result.documents[0].confidenceScores.negative;
			
			request.setAttribute("message", message);
			request.setAttribute("message2", message2);
			request.setAttribute("message3", message3);
			
			request.getRequestDispatcher("/WEB-INF/jsp/resultSentiment.jsp").forward(request, response);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String string = request.getParameter("string");
		request.setAttribute("string", string);

		try {
			Language1 result = Sentiment.getLanguage(string);
			String message = result.documents[0].confidenceScores.positive;
			String message2 = result.documents[0].confidenceScores.neutral;
			String message3 = result.documents[0].confidenceScores.negative;
			
			request.setAttribute("message", message);
			request.setAttribute("message2", message2);
			request.setAttribute("message3", message3);
			
			request.getRequestDispatcher("/WEB-INF/jsp/resultSentiment.jsp").forward(request, response);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}

