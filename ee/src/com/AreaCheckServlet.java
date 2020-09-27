package com;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    private ArrayList<Double> paramsX;
    private ArrayList<Double> paramsY;
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

    public ArrayList<Double> getParamsX ( ) {
        return paramsX;
    }

    public ArrayList<Double> getParamsY ( ) {
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
        paramsX = new ArrayList<>( );
        paramsY = new ArrayList<>( );
        paramsR = new ArrayList<>();
        this.request.setAttribute("AreaCheckServlet", this);

    }

    public void checkCoordFromGraphic (ArrayList<String> paramsR, String paramY) throws ServletException, IOException {

//
//        this.paramsR = paramsR;
//        newParamsR = new ArrayList<>( );
//        paramsX = new ArrayList<>( );
//        paramsY = new ArrayList<>( );
//
//
//        Long currentX = 0L;
//        Long currentY = 0L;
//
//
//        try {
//            currentX = Long.parseLong(paramY.substring(paramY.indexOf("c") + 1));
//            currentY = Long.parseLong(paramY.substring(0, paramY.indexOf("g")));
//        } catch (NumberFormatException ex) {
//            shit = true;
//        }
//
//        newParamsR = rFromStringsToIntegers(paramsR);
//        if (newParamsR.isEmpty( )) shit = true;
//
//
//        for (Long r : newParamsR) {
//            if (!shit) {
//                paramsX.add(currentX / 100d * r);
//                paramsY.add(currentY / 100d * r);
//
//                checkArea(paramsX.get(newParamsR.indexOf(r)), paramsY.get(newParamsR.indexOf(r)), r);
//            } else results.add("Данные неверны");
//
//        }
//
//
//        if (!shit) {
//            request.setAttribute("X", currentX);
//            request.setAttribute("Y", currentY);
//            servletContext.getRequestDispatcher("/index.jsp").forward(request, response);
//        }
//
//        else {
//            servletContext.getRequestDispatcher("/result.jsp").forward(request, response);
//        }

    }


    public ArrayList<String> getParamsR ( ) {
        return paramsR;
    }

    public void checkCoordFromForm (ArrayList<String> paramsR, String paramX, String paramY) throws ServletException, IOException {

        results = new String[paramsR.size()];

        for (int i=0; i<paramsR.size(); i++) {
            results[i] = " ";
        }

        this.paramsR = paramsR;

        newParamsR = new ArrayList<>( );
        newParamsR = rFromStringsToIntegers(paramsR);

        System.out.println(paramY);

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

        if (!shit) {
            int i=0;
            int countForNewR = 0;
            for (String s : paramsR) {
                if (!results[i].equals("Данные неверны")) {
                    results[i] = (checkArea(Double.parseDouble(newParamX.toString( )), newParamY, newParamsR.get(countForNewR)));
                    countForNewR++;
                }
                i++;
            }
        }



        servletContext.getRequestDispatcher("/result.jsp").forward(request, response);


    }

    public ArrayList<Long> rFromStringsToIntegers (ArrayList<String> paramsR) {
        ArrayList<Long> newParamsR = new ArrayList<>( );
        for (String r : paramsR) {
            try {
                Long newR = (Long.parseLong(r));
                if (newR != 1 && newR != 2 && newR != 3 && newR != 4 && newR != 5) {
                    results[paramsR.indexOf(r)] = "Данные неверны";
                    continue;
                }
                newParamsR.add(newR);

            } catch (NumberFormatException ex) {
                results[paramsR.indexOf(r)] = "Данные неверны";
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
