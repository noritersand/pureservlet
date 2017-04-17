package pureservlet.mvc;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = -5671289707695059116L;
	
	private static final String JSP_PREFIX = "/WEB-INF/jsp/";
	private static final String JSP_SUFIX = ".jsp";

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        process(req, resp);
    }

    private void forward(HttpServletRequest req, HttpServletResponse resp,
            String path) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(path);
        rd.forward(req, resp);
    }

    /**
     * 요청 파라미터를 콘솔에 출력
     */
    private void printParameters(HttpServletRequest req) {
        Map<String, String[]> parameterMap = req.getParameterMap();
        Set<String> keySet = parameterMap.keySet();
        Iterator<String> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            String[] values = (String[]) parameterMap.get(key);
            System.out.println(key + ": " + Arrays.toString(values));
        }
    }

    protected void process(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.printParameters(req);

		req.setCharacterEncoding("UTF-8");

		String contextPath = req.getContextPath();
		contextPath = contextPath.length() == 0 ? "/" : contextPath;

		String requestURI = req.getRequestURI();
		requestURI = (contextPath.length() > 1)
				? requestURI.substring(contextPath.length(), requestURI.length())
				: requestURI;

		System.out.println("contextPath: " + contextPath);
		System.out.println("requestURI: " + requestURI);

		requestURI = requestURI.substring(0, requestURI.lastIndexOf("."));
		StringBuilder builder = new StringBuilder(JSP_PREFIX).append(requestURI).append(JSP_SUFIX);
		forward(req, resp, builder.toString());
    }
}