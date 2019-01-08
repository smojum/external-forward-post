package com.dhp.clarity.claritywrapperservice;

import org.apache.wss4j.common.ext.WSPasswordCallback;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class UTPasswordCallback implements CallbackHandler {
    private Map<String, String> passwords = new HashMap<String, String>();


    public UTPasswordCallback() {
        final Properties properties = new Properties();
        try {
            //TODO, get this from bootstrap or application properties in correct order.
            properties.load(UTPasswordCallback.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String clarityAliasName = properties.getProperty("clarity.cert.alias.name");
        String clarityAliasPassword = properties.getProperty("clarity.cert.alias.password");
        String deanAliasName = properties.getProperty("dean.cert.alias.name");
        String deanAliasPassword = properties.getProperty("dean.cert.alias.password");
        passwords.put(clarityAliasName, clarityAliasPassword);
        passwords.put(deanAliasName, deanAliasPassword);
    }

    /**
     * Here, we attempt to get the password from the private alias/passwords
     * map.
     */
    @Override
    public void handle(Callback[] callbacks) {
        for (int i = 0; i < callbacks.length; i++) {
            WSPasswordCallback pc = (WSPasswordCallback) callbacks[i];

            String pass = passwords.get(pc.getIdentifier());
            if (pass != null) {
                pc.setPassword(pass);
                return;
            }
        }
    }
}
