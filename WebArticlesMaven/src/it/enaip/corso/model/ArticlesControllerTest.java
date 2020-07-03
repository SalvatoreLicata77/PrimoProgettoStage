package it.enaip.corso.model;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.Test;


public class ArticlesControllerTest {

	@Test
	public void testDoPostHttpServletRequestHttpServletResponse() {
		fail("Not yet implemented");
	}

	@Test
	public void testDoGetHttpServletRequestHttpServletResponse() {
		fail("Not yet implemented");
	}

	@Test
	public void testDoGetHttpServletRequestHttpServletResponse1() throws SQLException, ServletException, IOException {

		ArticlesController servlet = new ArticlesController();
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		DbConnect.getInstance().getConnection();
		final String nm= "name";
		
	    when(request.getParameter(nm)).thenReturn("testName");
	    when(request.getParameter("description")).thenReturn("testDescription");
	    when(request.getParameter("quantity")).thenReturn("1");
	    when(request.getParameter("location")).thenReturn("testLocation");
	    servlet.insertArticles(request, response);
	    assertTrue("check", nm.equals("testName"));
	    
	}

	@Test
	public void testDoPostHttpServletRequestHttpServletResponse1() {
		fail("Not yet implemented");
	}

}
