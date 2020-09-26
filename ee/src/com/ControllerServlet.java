package com;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;


public class ControllerServlet extends HttpServlet {
    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println(1);

        String[] paramsR = request.getParameterValues("chooseR");
        String paramX = request.getParameter("selectX");
        String paramY = request.getParameter("enterY");
        ArrayList<String> emptyparams = new ArrayList<>( );

        if (paramY != null && paramY.contains("graphic")) {
            if (paramsR == null) {
                emptyparams.add("R");
                request.setAttribute("emptyparams", emptyparams);
                this.getServletContext( ).getRequestDispatcher("/index.jsp").forward(request, response);
            }
            else {

            }
        }

        else {
            if (paramsR == null || paramX == null || paramY == null) {
                if (paramsR == null) {
                    emptyparams.add("R");
                }
                if (paramX == null) {
                    emptyparams.add("X");
                }
                if (paramY == null) {
                    emptyparams.add("Y");
                }

                request.setAttribute("emptyparams", emptyparams);
                this.getServletContext( ).getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }


    }
}
