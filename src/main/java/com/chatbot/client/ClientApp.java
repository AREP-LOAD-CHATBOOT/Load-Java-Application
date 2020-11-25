package com.chatbot.client;

import com.ibm.cloud.sdk.core.http.HttpConfigOptions;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.assistant.v2.Assistant;
import com.ibm.watson.assistant.v2.model.*;

public class ClientApp {

    private Assistant assistant;

    private static String assistantId = "05d9ce45-0098-4bcb-83e5-da0388a669a2";

    private String sessionId;

    private String session;

    public ClientApp(){
        IamAuthenticator authenticator = new IamAuthenticator(System.getenv("API_KEY"));
        assistant = new Assistant("2020-11-11", authenticator);
        assistant.setServiceUrl("https://api.us-south.assistant.watson.cloud.ibm.com");

        HttpConfigOptions configOptions = new HttpConfigOptions.Builder()
                .disableSslVerification(true)
                .build();
        assistant.configureClient(configOptions);
        CreateSessionOptions options = new CreateSessionOptions.Builder(assistantId).build();
        SessionResponse response = assistant.createSession(options).execute().getResult();
        sessionId = response.getSessionId();


    }

    public String sendMessage(String menssage){
        MessageInput input = new MessageInput.Builder()
                .messageType("text")
                .text(menssage)
                .build();

        MessageOptions messageOptions = new MessageOptions.Builder(assistantId, sessionId)
                .input(input)
                .build();
        MessageResponse response = assistant.message(messageOptions).execute().getResult();
        return response.getOutput().getGeneric().get(0).text();
    }


}
