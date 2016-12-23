/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.wap472.service;

import com.sun.xml.ws.mex.client.schema.GetMetadata;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Rojan
 */
public class SearchServiceImp implements SearchService {

    @Override
    public String search(String s) {
        Connection connection = DBConnection.getConnection();

        PreparedStatement stmt = null;

        ResultSet rSet = null;

        String result = null;

        String query = "SELECT wordtype, definition from entries where word=?;";

        try{
            stmt = connection.prepareStatement(query);
            stmt.setString(1, s);
            rSet = stmt.executeQuery();
            result = convertToJSON(rSet).toString();
        } catch(JSONException e){
            
        } catch(SQLException ex){
            Logger.getLogger(SearchServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(connection);
        }
        
        
        
        return result;

    }
    
    private JSONArray convertToJSON (ResultSet resultSet) throws SQLException, JSONException {
    JSONArray jsonArray = new JSONArray();
    
    while(resultSet.next()){
        int totalRows = resultSet.getMetaData().getColumnCount();
        JSONObject obj = new JSONObject();
        for(int i = 0; i < totalRows; i++){
            obj.put(resultSet.getMetaData().getColumnLabel(i+1).toLowerCase(), resultSet.getObject(i +1));
        }
        jsonArray.put(obj);
    }
    
    return jsonArray;
    }

}
