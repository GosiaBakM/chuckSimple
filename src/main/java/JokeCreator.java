import org.codehaus.jackson.map.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class JokeCreator {

    Set<String> jokeIdSet = new HashSet<String>();

    public void getJson() throws IOException {
        URL urlUse = new URL("https://api.chucknorris.io/jokes/random");
        HttpURLConnection connection = (HttpURLConnection) urlUse.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-length", "application/json");
        connection.connect();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuffer response = new StringBuffer();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        ObjectMapper objectMapper = new ObjectMapper();
        Joke joke = new Joke();
        joke = objectMapper.readValue(response.toString(), Joke.class);
        //System.out.println(joke.value);
        //if(joke!=null)

        if (jokeIdSet.isEmpty()) {
            System.out.println(joke.value);
        } else {
            for (String j : jokeIdSet) {
                if (!joke.getId().equals(j)) {
                    System.out.println(joke.value);
                    jokeIdSet.add(joke.getId());
                }
                else return;
            }
        }
    }
}


