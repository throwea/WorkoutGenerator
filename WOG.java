import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.*;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.AppendValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;

public class WOG {
    private static Sheets sheetsService;
    private static final String APPLICATION_NAME = "Application name";
    private static final String SPREADSHEET_ID = "16IwExxMlsptbPg-qKY05YmfF0mWxVBqqZDI-lVD3DsE";

    //TODO: Create method for reading keys and return Map<String, String>
    //private static final Map<String, String> personal_links = readKeys("keys.txt")


    private static Credential authorize() throws IOException, GeneralSecurityException {
        InputStream in = WOG.class.getResourceAsStream("/credentials.json");
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
                JacksonFactory.getDefaultInstance(), new InputStreamReader(in)
        );
        List<String> scopes = Arrays.asList(SheetsScopes.SPREADSHEETS);
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(),
                clientSecrets, scopes)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File("tokens")))
                .setAccessType("offline")
                .build();
        Credential credential = new AuthorizationCodeInstalledApp(
                flow, new LocalServerReceiver())
                .authorize("user");

        return credential;
    }
    public static Sheets getSheetsService() throws IOException, GeneralSecurityException {
        Credential credential = authorize();
        return new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(),
                JacksonFactory.getDefaultInstance(), credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
    public static void appendOperation(ArrayList<String> row, String link) throws IOException, GeneralSecurityException {
        sheetsService = getSheetsService();
        //====================================

        //====================================
        ValueRange appendBody = new ValueRange()
                .setValues(Arrays.asList(
                        Arrays.asList(row.toArray())
                ));
        AppendValuesResponse appendResult = sheetsService.spreadsheets().values()
                .append(link, "A1", appendBody)
                .setValueInputOption("USER_ENTERED")
                .setInsertDataOption("INSERT_ROWS")
                .setIncludeValuesInResponse(true)
                .execute();

    }
    public static void generateWorkout(int count, int weeks_to_train, String name) throws IOException, GeneralSecurityException {


        for (int j = 1; j <= weeks_to_train; j++){
            int week;
            if (j < 5) week = j;
            else week = j % 4;
            WorkoutCompiler wog = new WorkoutCompiler(count, week);
            for (int i = 0; i < wog.configuredTemplate.size(); i++){
                appendOperation(wog.configuredTemplate.get(i), personal_links.get(name.toLowerCase()));
            }
        }


    }
    public void writeToSpreadSheet(){
        return;
    }

    public static void main(String[] args) throws IOException, GeneralSecurityException {
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        int days_per_week = in.nextInt();
        System.out.println("Which person are you writing the program for?");
        System.out.println("How many days a week can they lift");
        generateWorkout(5, 4, name);

    }
}
