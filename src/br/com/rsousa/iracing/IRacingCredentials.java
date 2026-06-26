package br.com.rsousa.iracing;

import java.io.*;
import java.nio.file.*;
import java.util.Properties;

public class IRacingCredentials {

    private static final String FILE_PATH =
            System.getProperty("user.home") + "/.race-results/iracing.properties";

    private String email = "";
    private String password = "";
    private String clientId = "";
    private String clientSecret = "";

    public static IRacingCredentials load() {
        IRacingCredentials creds = new IRacingCredentials();
        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get(FILE_PATH))) {
            props.load(in);
            creds.email        = props.getProperty("email", "");
            creds.password     = props.getProperty("password", "");
            creds.clientId     = props.getProperty("client_id", "");
            creds.clientSecret = props.getProperty("client_secret", "");
        } catch (IOException ignored) {}
        return creds;
    }

    public void save() throws IOException {
        Path path = Paths.get(FILE_PATH);
        Files.createDirectories(path.getParent());
        Properties props = new Properties();
        props.setProperty("email",         email);
        props.setProperty("password",      password);
        props.setProperty("client_id",     clientId);
        props.setProperty("client_secret", clientSecret);
        try (OutputStream out = Files.newOutputStream(path)) {
            props.store(out, "iRacing API Credentials");
        }
    }

    public boolean isConfigured() {
        return !email.isEmpty() && !password.isEmpty()
                && !clientId.isEmpty() && !clientSecret.isEmpty();
    }

    public String getEmail()              { return email; }
    public void   setEmail(String v)      { this.email = v; }
    public String getPassword()           { return password; }
    public void   setPassword(String v)   { this.password = v; }
    public String getClientId()           { return clientId; }
    public void   setClientId(String v)   { this.clientId = v; }
    public String getClientSecret()       { return clientSecret; }
    public void   setClientSecret(String v){ this.clientSecret = v; }
}
