package hello.servlet.web.frontcontroller.v1.controller;

import hello.servlet.web.frontcontroller.v1.ControllerV1;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberFormControllerV1 implements ControllerV1 {

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";
        //controller -> view
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        //다른 서블릿이나 JSP로 이동할 수 있는 기능.
        dispatcher.forward(request,response);
    }
}
