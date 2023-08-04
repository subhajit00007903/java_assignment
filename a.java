import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class a{

    public static void main(String[] args) {
        String url = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp";
        String loginId = "test@sunbasedata.com";
        String password = "Test@123";

        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

            // Set the request method to POST
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            // Build the request body as JSON
            String requestBody = "{\"login_id\":\"" + loginId + "\",\"password\":\"" + password + "\"}";

            // Send the request
            connection.setDoOutput(true);
            OutputStream os = connection.getOutputStream();
            os.write(requestBody.getBytes());
            os.flush();
            os.close();

            // Get the response
            int responseCode = connection.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            if (responseCode == 200) {
                // Authentication successful
                String token = response.toString(); // Assuming the response contains only the token
                System.out.println("Bearer Token: " + token);
            } else {
                // Authentication failed
                System.out.println("Authentication failed. Response Code: " + responseCode);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
