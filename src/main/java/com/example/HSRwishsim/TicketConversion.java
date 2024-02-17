package com.example.HSRwishsim;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;


public class TicketConversion extends SceneDisplay{

    @FXML
    Button convertButton;
    @FXML
    Button backButton;
    @FXML
    Label JadeAmount;
    @FXML
    Label StandardTicketAmount;
    @FXML
    Label EventTicketAmount;
    @FXML
    TextField eventTicketInputAmount;
    @FXML
    TextField standardTicketInputAmount;

    private final int jadeAmount = AppInfo.getInstance().getJade();
    private final int standardTicketAmount = AppInfo.getInstance().getStandardTickets();
    private final int eventTicketAmount = AppInfo.getInstance().getEventTickets();

    public void initialize(){
        JadeAmount.setText("Jade Amount: " + jadeAmount);
        StandardTicketAmount.setText("Current Standard Tickets: " + standardTicketAmount);
        EventTicketAmount.setText("Current Event Tickets: " + eventTicketAmount);
    }

    public void ticketConversion(ActionEvent event) {
        String inputEventTickets = eventTicketInputAmount.getText();
        String inputStandardTickets = standardTicketInputAmount.getText();
        int eventTickets;
        int standardTickets;

        try {
            eventTickets = Integer.parseInt(inputEventTickets);
            if(eventTickets < 0){
                displayAlert("ERROR: Please enter a NON-negative number for event tickets.");
                return;
            }
        } catch (NumberFormatException e) {
            displayAlert("ERROR: Please enter a valid number for event tickets!");
            return;
        }

        try {
            standardTickets = Integer.parseInt(inputStandardTickets);
            if(standardTickets < 0){
                displayAlert("ERROR: Please enter a NON-negative number for standard tickets.");
                return;
            }
        } catch (NumberFormatException e) {
            displayAlert("ERROR: Please enter a valid number for standard tickets!");
            return;
        }

        convertJadeToTickets(standardTickets,eventTickets);
        switchToCurrentBanner(event);
    }

    private void convertJadeToTickets(int standardTickets, int eventTickets){
        int standardTicketConversionCost = standardTickets * 160;
        int eventTicketConversionCost = eventTickets * 160;

        if (jadeAmount >= standardTicketConversionCost + eventTicketConversionCost) {
            int newJadeAmount = jadeAmount - (standardTicketConversionCost + eventTicketConversionCost);
            AppInfo.getInstance().setJades(newJadeAmount);

            int newStandardTicketAmount = standardTicketAmount + standardTickets;
            AppInfo.getInstance().setStandardTickets(newStandardTicketAmount);
            System.out.println("Added " + standardTickets + " standard tickets to your balance");

            int newEventTicketAmount = eventTicketAmount + eventTickets;
            AppInfo.getInstance().setEventTickets(newEventTicketAmount);
            System.out.println("Added " + eventTickets + " event tickets to your balance.");
        } else {
            System.out.println("Not enough jades for ticket conversion.");
        }
    }

    private void displayAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void switchToCurrentBanner(ActionEvent event) {
        try {
            if (EventBanner.ticketConversion) {
                super.displayScene("eventBanner.fxml", event);
                EventBanner.ticketConversion = false;
            } else if (LightConeBanner.ticketConversion) {
                super.displayScene("lightConeBanner.fxml", event);
                LightConeBanner.ticketConversion = false;
            } else if (StandardBanner.ticketConversion) {
                super.displayScene("standardBanner.fxml", event);
                StandardBanner.ticketConversion = false;
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
