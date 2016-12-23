/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.wap472.com;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mum.wap472.service.SearchService;
import mum.wap472.service.SearchServiceImp;

/**
 *
 * @author 985368
 */
public class DictServlet extends HttpServlet {
    SearchService sService = new SearchServiceImp();
    
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.print("<html><head><title>Test</title></head><body>");
        out.print("<form method='post'>");
        out.print("<p>Please click the button</p>");
        out.print("<input type='submit' value='Click me'/>");
        out.print("</form>");
        out.print("</body></html>");
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
            
        String result = sService.search(request.getParameter("searchkey"));
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(result);
        
    }

}
