package BLL;

import DLL.DBConnect;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AgentDB {
    /*
     * Purpose: Communicates between the agent object, and the database to complete operations.
     * Author: Brent Ward
     * Module: PROJ-207-OSD
     * Date May 15, 2019
     * */

    //Gets list of agents
    public static List<Agent> getAgents(){
        List<Agent> agents = null;

        try{
            //connection built
            Connection connect = DBConnect.getConnection();

            //query
            String selectQuery = "select AgentId, AgtFirstName, AgtMiddleInitial, AgtLastName, AgtBusPhone," +
                                    "AgtEmail, AgtPosition, AgencyId from Agents";

            //makes a sql statement
            Statement query = connect.createStatement();

            //assigns & executes statement
            ResultSet rs = query.executeQuery(selectQuery);

            agents = new ArrayList<Agent>();
            //runs while reader has data
            while(rs.next()){
                Agent agent = new Agent(rs.getInt("AgentId"),
                                        rs.getString("AgtFirstName"),
                                        rs.getString("AgtMiddleInitial"),
                                        rs.getString("AgtLastName"),
                                        rs.getString("AgtBusPhone"),
                                        rs.getString("AgtEmail"),
                                        rs.getString("AgtPosition"),
                                        rs.getInt("AgencyId"));
                agents.add(agent);
            }
            connect.close();

            }catch(Exception e) { e.printStackTrace(); }

        return agents;
    }

    //grabs a singular agent
    public static Agent grabAgent(int id){
        Agent agent = null;

        try {
            //connection built
            Connection connect = DBConnect.getConnection();

            //query
            String selectQuery = "select AgentId, AgtFirstName, AgtMiddleInitial, AgtLastName, AgtBusPhone," +
                    "AgtEmail, AgtPosition, AgencyId from Agents where AgentId=?";

            //makes a sql statement
            PreparedStatement stmt = connect.prepareStatement(selectQuery);
            stmt.setInt(1, id);

            //assigns and executes statement
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                agent = new Agent(rs.getInt("AgentId"),
                        rs.getString("AgtFirstName"),
                        rs.getString("AgtMiddleInitial"),
                        rs.getString("AgtLastName"),
                        rs.getString("AgtBusPhone"),
                        rs.getString("AgtEmail"),
                        rs.getString("AgtPosition"),
                        rs.getInt("AgencyId"));
            }
            connect.close();

        }catch(Exception e){ e.printStackTrace(); }

        return agent;
    }

    //searchs an agent based on user input
    public static List<Agent> searchAgents(String name){
        List<Agent> agents = null;

        try{
            //connection built
            Connection connect = DBConnect.getConnection();

            //query
            String selectQuery = "select AgentId, AgtFirstName, AgtMiddleInitial, AgtLastName, AgtBusPhone," +
                    "AgtEmail, AgtPosition, AgencyId from Agents where AgtFirstName like ? or AgtLastName like ?";

            //makes a sql statement
            PreparedStatement stmt = connect.prepareStatement(selectQuery);
            stmt.setString(1, '%' + name + '%');
            stmt.setString(2, '%' + name + '%');

            //assigns & executes statement
            ResultSet rs = stmt.executeQuery();

            agents = new ArrayList<Agent>();
            //runs while reader has data
            while(rs.next()){
                Agent agent = new Agent(rs.getInt("AgentId"),
                        rs.getString("AgtFirstName"),
                        rs.getString("AgtMiddleInitial"),
                        rs.getString("AgtLastName"),
                        rs.getString("AgtBusPhone"),
                        rs.getString("AgtEmail"),
                        rs.getString("AgtPosition"),
                        rs.getInt("AgencyId"));
                agents.add(agent);
            }
            connect.close();

        }catch(Exception e) { e.printStackTrace(); }

        return agents;
    }


    //inserts an Agent
    public static void addAgent(Agent agent){
        try{
            //connection built
            Connection connect = DBConnect.getConnection();

            //query
            String insertQuery = "Insert into Agents(AgtFirstName, AgtMiddleInitial, AgtLastName, AgtBusPhone, AgtEmail, " +
                    "AgtPosition, AgencyId) values(?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = connect.prepareStatement(insertQuery);

            //sets parameters for ?
            stmt.setString(1, agent.getFirstName());
            stmt.setString(2, agent.getMiddleInitial());
            stmt.setString(3, agent.getLastName());
            stmt.setString(4, agent.getPhone());
            stmt.setString(5, agent.getEmail());
            stmt.setString(6, agent.getPosition());
            stmt.setInt(7, agent.getAgency());

            //checks if the data was inserted
            int numRows = stmt.executeUpdate();
            if (numRows == 0)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Agent failed to add. Contact Tech Support.");
                alert.showAndWait();
            }
            connect.close();

        }catch(Exception e) { e.printStackTrace(); }
    }


    //updates an Agent
    public static void updateAgent(Agent agent){
        try{
            //connection built
            Connection connect = DBConnect.getConnection();

            //query
            String updateQuery = "update Agents set AgtFirstName=?, AgtMiddleInitial=?, AgtLastName=?, AgtBusPhone=?," +
                                    "AgtEmail=?, AgtPosition=?, AgencyId=? where AgentId=?";

            PreparedStatement stmt = connect.prepareStatement(updateQuery);

            //sets parameters for ?

            stmt.setString(1, agent.getFirstName());
            stmt.setString(2, agent.getMiddleInitial());
            stmt.setString(3, agent.getLastName());
            stmt.setString(4, agent.getPhone());
            stmt.setString(5, agent.getEmail());
            stmt.setString(6, agent.getPosition());
            stmt.setInt(7, agent.getAgency());
            stmt.setInt(8, agent.getID());

            //checks if the data was inserted
            int numRows = stmt.executeUpdate();
            if (numRows == 0)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Agent failed to update. Contact Tech Support.");
                alert.showAndWait();
            }
            connect.close();

        }catch(Exception e) { e.printStackTrace(); }
    }


    //deletes an Agent
    public static void deleteAgent(Agent agent){
        try{
            //connection built
            Connection connect = DBConnect.getConnection();

            String deleteQuery = "delete from Agents where AgentId=?";

            PreparedStatement stmt = connect.prepareStatement(deleteQuery);
            stmt.setInt(1, agent.getID());

            //checks if agent is deleted
            int numRows = stmt.executeUpdate();
            if(numRows == 0){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Agent failed to delete. Contact Tech Support.");
                alert.showAndWait();
            }

            connect.close();

        }catch(Exception e) { e.printStackTrace(); }
    }
}
