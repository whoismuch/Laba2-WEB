package com;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import helpers.*;

public class AreaCheckServlet extends HttpServlet {

    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] paramsR = request.getParameterValues("chooseR");
        String paramX = request.getParameter("selectX");
        String paramY = request.getParameter("enterY");
        ArrayList<FullResult> historyList;
        ArrayList<Point> pointsList;

        if (request.getServletContext( ).getAttribute("history") == null) {
            historyList = new ArrayList<>( );
        } else {
            historyList = (ArrayList<FullResult>) request.getServletContext( ).getAttribute("history");
        }

        if (paramY != null && paramY.contains("graphic")) {

            for (int i = 0; i < paramsR.length; i++) {
                try {
                    int currentX = Integer.parseInt(paramY.substring(paramY.indexOf("c") + 1));
                    int currentY = Integer.parseInt(paramY.substring(0, paramY.indexOf("g")));

                    int r = Integer.parseInt(paramsR[i]);
                    if (r < 1 || r > 5) throw new NumberFormatException( );
                    double x = (currentX / 100d * r);
                    double y = (currentY / 100d * r);
                    int result = checkArea(x, y, r);

                    FullResult currentFullResult = new FullResult(x, y, r, result);
                    historyList.add(currentFullResult);

                } catch (NumberFormatException ex) {
                    FullResult currentFullResult = new FullResult(0, 0, 0, -1);
                    historyList.add(currentFullResult);
                    continue;
                }
            }
        } else {

            for (int i = 0; i < paramsR.length; i++) {
                try {
                    int x = Integer.parseInt(paramX);
                    double y = Double.parseDouble(paramY);

                    int r = Integer.parseInt(paramsR[i]);
                    if (x < -5 || x > 3 || y < -3 || y > 5 || r < 1 || r > 5) throw new NumberFormatException( );
                    int result = checkArea(x, y, r);

                    FullResult currentFullResult = new FullResult(x, y, r, result);
                    historyList.add(currentFullResult);

                } catch (NumberFormatException ex) {
                    FullResult currentFullResult = new FullResult(0, 0, 0, -1);
                    historyList.add(currentFullResult);
                    continue;
                }
            }
        }

        pointsList = makeAPointsFromHistory(historyList);
        request.getServletContext( ).setAttribute("history", historyList);
        request.getServletContext( ).setAttribute("points", pointsList);

        request.getServletContext( ).getRequestDispatcher("/index.jsp").forward(request, response);
    }

    private ArrayList<Point> makeAPointsFromHistory (ArrayList<FullResult> historyList) {
        ArrayList<Point> pointsList = new ArrayList<>( );
        for (FullResult fullResult : historyList) {
            if (fullResult.getResult( ) != -1) {
                pointsList.add(new Point(fullResult.getX( ).doubleValue( ), fullResult.getY( ).doubleValue( ), fullResult.getR( ).intValue( )));
            }
        }
        return pointsList;
    }

    public int checkArea (double x, double y, int r) {
        if (x * x + y * y <= r * r && x <= 0 && y >= 0
                || x >= 0 && x <= r / 2d && y <= 0 && y >= -r
                || x <= 0 && y <= 0 && y >= -2 * x - r) {
            return 0;
        } else return 1;
    }
}
