package com;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AreaCheckServlet {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private ServletContext servletContext;
    //Все переданные наборы данных невалидны ->  shit = true
    private Boolean shit;

    //Номера будующих строк с невалидными данными
    private ArrayList<Integer> stringsWithShit;
    private ArrayList<Long> newParamsR;
    private Double[] paramsX;
    private Double[] paramsY;
    private String[] results;
    private Long newParamX;
    private Double newParamY;
    private ArrayList<String> paramsR;


    public HttpServletRequest getRequest ( ) {
        return request;
    }

    public HttpServletResponse getResponse ( ) {
        return response;
    }

    public ServletContext getServletContext ( ) {
        return servletContext;
    }

    public Boolean getShit ( ) {
        return shit;
    }

    public ArrayList<Integer> getStringsWithShit ( ) {
        return stringsWithShit;
    }

    public ArrayList<Long> getNewParamsR ( ) {
        return newParamsR;
    }

    public Double[] getParamsX ( ) {
        return paramsX;
    }

    public Double[] getParamsY ( ) {
        return paramsY;
    }

    public String[] getResults ( ) {
        return results;
    }

    public Long getNewParamX ( ) {
        return newParamX;
    }

    public Double getNewParamY ( ) {
        return newParamY;
    }

    public AreaCheckServlet (HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) {
        this.request = request;
        this.response = response;
        this.servletContext = servletContext;

        shit = false;
        stringsWithShit = new ArrayList<>( );
        newParamsR = new ArrayList<>( );
        paramsR = new ArrayList<>( );
        this.request.setAttribute("AreaCheckServlet", this);

    }

    public void checkCoordFromGraphic (ArrayList<String> paramsR, String paramY) throws ServletException, IOException {

        results = new String[paramsR.size( )];
        paramsX = new Double[paramsR.size( )];
        paramsY = new Double[paramsR.size( )];

        for (int i = 0; i < paramsR.size( ); i++) {
            results[i] = " ";
            paramsX[i] = 0d;
            paramsY[i] = 0d;

        }

//
        this.paramsR = paramsR;


        Long currentX = 0L;
        Long currentY = 0L;


        try {
            currentX = Long.parseLong(paramY.substring(paramY.indexOf("c") + 1));
            currentY = Long.parseLong(paramY.substring(0, paramY.indexOf("g")));
        } catch (NumberFormatException ex) {
            shit = true;
        }

        newParamsR = rFromStringsToIntegers(paramsR);
        if (newParamsR.isEmpty( )) shit = true;


        if (shit) {
            for (int i = 0; i < paramsR.size( ); i++) {
                results[i] = "Данные неверны";
            }
        }

        int countForNewR = 0;
        boolean in = false;
        for (int i = 0; i < paramsR.size( ); i++) {
            if (!results[i].equals("Данные неверны")) {
                paramsX[i] = (currentX / 100d * newParamsR.get(countForNewR));
                paramsY[i] = (currentY / 100d * newParamsR.get(countForNewR));
                results[i] = (checkArea(paramsX[i], paramsY[i], newParamsR.get(countForNewR)));
                countForNewR++;
                in = true;
            }
            HashMap<String, Object> datas = new HashMap<>( );
            ArrayList<Number> points = new ArrayList<>( );

            if (!shit) {
                datas.put("X", paramsX[i]);
                datas.put("Y", paramsY[i]);
                if (in) datas.put("R", newParamsR.get(countForNewR - 1));
                else datas.put("R", newParamsR.get(countForNewR));
            }
            datas.put("Result", results[i]);


            ArrayList<HashMap<String, Object>> historyList;
            ArrayList<ArrayList<Number>> pointsList;
            if (servletContext.getAttribute("history") == null) {
                historyList = new ArrayList<>( );
                pointsList = new ArrayList<>( );
            } else {
                historyList = (ArrayList<HashMap<String, Object>>) servletContext.getAttribute("history");
                pointsList = (ArrayList<ArrayList<Number>>) servletContext.getAttribute("points");
            }
            historyList.add(datas);
            if (!results[i].equals("Данные неверны")) {
                points.add(paramsX[i]);
                points.add(paramsY[i]);
                points.add(newParamsR.get(countForNewR - 1));
                pointsList.add(points);
            }

            servletContext.setAttribute("history", historyList);
            servletContext.setAttribute("points", pointsList);
        }


        servletContext.getRequestDispatcher("/index.jsp").forward(request, response);
//        servletContext.getRequestDispatcher("/result.jsp").forward(request, response);


    }


    public void checkCoordFromForm (ArrayList<String> paramsR, String paramX, String paramY) throws ServletException, IOException {

        results = new String[paramsR.size( )];
        paramsX = new Double[paramsR.size( )];
        paramsY = new Double[paramsR.size( )];

        for (int i = 0; i < paramsR.size( ); i++) {
            results[i] = " ";
        }

        this.paramsR = paramsR;

        newParamsR = new ArrayList<>( );
        newParamsR = rFromStringsToIntegers(paramsR);
        if (newParamsR.isEmpty( )) shit = true;

        try {
            newParamX = Long.parseLong(paramX);
            newParamY = Double.parseDouble(paramY);
        } catch (NumberFormatException ex) {
            shit = true;
        }

        try {
            if (newParamX < -5 || newParamX > 3 || newParamY < -3 || newParamY > 5) {
                shit = true;
            }
        } catch (NullPointerException ex) {
            shit = true;
        }


        if (shit) {
            for (int i = 0; i < paramsR.size( ); i++) {
                results[i] = "Данные неверны";
            }
        }

        int countForNewR = 0;
        boolean in = false;
        for (int i = 0; i < paramsR.size( ); i++) {
            if (!results[i].equals("Данные неверны")) {
                results[i] = (checkArea(Double.parseDouble(newParamX.toString( )), newParamY, newParamsR.get(countForNewR)));
                countForNewR++;
                in = true;
            }

            HashMap<String, Object> datas = new HashMap<>( );
            ArrayList<Number> points = new ArrayList<>( );
            if (!shit) {
                datas.put("X", newParamX);
                datas.put("Y", newParamY);
                if (in) datas.put("R", newParamsR.get(countForNewR - 1));
                else datas.put("R", newParamsR.get(countForNewR));
            }
            datas.put("Result", results[i]);


            ArrayList<HashMap<String, Object>> historyList;
            ArrayList<ArrayList<Number>> pointsList;
            if (servletContext.getAttribute("history") == null) {
                historyList = new ArrayList<>( );
                pointsList = new ArrayList<>( );
            } else {
                historyList = (ArrayList<HashMap<String, Object>>) servletContext.getAttribute("history");
                pointsList = (ArrayList<ArrayList<Number>>) servletContext.getAttribute("points");
            }
            historyList.add(datas);
            if (!results[i].equals("Данные неверны")) {
                points.add(newParamX);
                points.add(newParamY);
                points.add(newParamsR.get(countForNewR - 1));
                pointsList.add(points);
            }

            servletContext.setAttribute("history", historyList);
            servletContext.setAttribute("points", pointsList);
        }


        servletContext.getRequestDispatcher("/index.jsp").forward(request, response);
//        servletContext.getRequestDispatcher("/result.jsp").forward(request, response);


    }

    public ArrayList<Long> rFromStringsToIntegers (ArrayList<String> paramsR) {
        ArrayList<Long> newParamsR = new ArrayList<>( );
        for (String r : paramsR) {
            try {
                Long newR = (Long.parseLong(r));
                if (newR != 1 && newR != 2 && newR != 3 && newR != 4 && newR != 5) {
                    results[paramsR.indexOf(r)] = "Данные неверны";
                    paramsX[paramsR.indexOf(r)] = null;
                    paramsY[paramsR.indexOf(r)] = null;
                    continue;
                }
                newParamsR.add(newR);

            } catch (NumberFormatException ex) {
                results[paramsR.indexOf(r)] = "Данные неверны";
                paramsX[paramsR.indexOf(r)] = null;
                paramsY[paramsR.indexOf(r)] = null;
                continue;
            }
        }
        return newParamsR;
    }

    public String checkArea (Double paramX, Double paramY, Long paramR) {
        if (paramX * paramX + paramY * paramY <= paramR * paramR && paramX <= 0 && paramY >= 0
                || paramX >= 0 && paramX <= paramR / 2d && paramY <= 0 && paramY >= -paramR
                || paramX <= 0 && paramY <= 0 && paramY >= -2 * paramX - paramR) {
            return ("А ты хорош");
        } else return ("Мимо");
    }
}
