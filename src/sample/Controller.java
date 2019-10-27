package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.net.SocketException;
import java.io.IOException;

public class Controller {

    @FXML
    TextArea outputMessage;

    @FXML
    TextField inputMessage;

    @FXML
    Button sendButton;

    @FXML
    Button connectionButton;

    private boolean connectEnable = false;

    Client client = new Client();

    public void initialize(){
        setDisable();
    }
    public void setDisable(){
        connectEnable=false;
        //outputMessage.setDisable(true);
        inputMessage.setDisable(true);
        sendButton.setDisable(true);
        connectionButton.setText("Connect");
        connectionButton.setStyle("-fx-background-color: rgb(198, 31, 31)");
    }
    public void setEnable(){
        connectEnable= true;
        inputMessage.setDisable(false);
        //outputMessage.setDisable(false);
        sendButton.setDisable(false);
        connectionButton.setText("Disconnect");
        connectionButton.setStyle("-fx-background-color: rgba(195,248,0,0.85)");
    }

    public void connect(ActionEvent event){
        try{
            if(!connectEnable){
                client.startClient();
                outputMessage.appendText("Enter the base of the truncated pyramid and a height of the through space :\n");
                setEnable();
            }
            else{
                client.disconnect();
                setDisable();
            }
        }
        catch(IOException e){
            setDisable();
            System.err.println("Error connection");
            e.printStackTrace();
        }
    }
    public void sendMessenge(ActionEvent actionEvent) {
        try {
            if (!client.isClosed()) {
                if (inputMessage.getText() != null || !inputMessage.getText().equals("")) {
                    try {
                        client.request(inputMessage.getText());
                        outputMessage.appendText("message: " + inputMessage.getText() + "\n");
                        inputMessage.setText("");
                        outputMessage.appendText("Answer from server: " + client.response() + "\n\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
               setDisable();
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }
}
