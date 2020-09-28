package com;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ControllerServlet extends HttpServlet {

    ServletContext sc;

    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String[] paramsR = request.getParameterValues("chooseR");
        String paramX = request.getParameter("selectX");
        String paramY = request.getParameter("enterY");
        ArrayList<String> emptyparams = new ArrayList<>( );
        AreaCheckServlet areaCheckServlet = new AreaCheckServlet(request, response, sc);


        if (paramY != null && paramY.contains("graphic")) {
            if (paramsR == null) {
                emptyparams.add("R");
                request.setAttribute("emptyparams", emptyparams);
                sc.getRequestDispatcher("/index.jsp").forward(request, response);
            } else {
                areaCheckServlet.checkCoordFromGraphic(Stream.of(paramsR).collect(Collectors.toCollection(ArrayList::new)), paramY);
            }
        } else {
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
                sc.getRequestDispatcher("/index.jsp").forward(request, response);
            } else {
                areaCheckServlet.checkCoordFromForm(Stream.of(paramsR).collect(Collectors.toCollection(ArrayList::new)), paramX, paramY);
            }
        }
    }


    public void init (ServletConfig config) throws ServletException {
        sc = config.getServletContext( );
    }

}
