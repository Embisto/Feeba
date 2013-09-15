package com.feeba.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feeba.data.ReturnDataController;


@SuppressWarnings("serial")
public class MainServlet extends HttpServlet
{

    public MainServlet()
    {
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	// not needed
    }
    @Override
    
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
        {
    	
    	ReturnDataController.newData(convertStreamToString(req.getInputStream()));
    	resp.getWriter().write("OK");
    	
        }
    
    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
