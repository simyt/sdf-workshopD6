package sg.edu.nus.iss.WorkshopD6.server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cookie {
    public static String getRandomCookie (String cookieFilePath) {
        String randomCookie = "";
        List<String> cookies = new ArrayList<>();
        try{
            cookies = getDataFromFile(cookieFilePath); 
            Random rand = new Random();
            int randVal = rand.nextInt(cookies.size());
            randomCookie = cookies.get(randVal);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return randomCookie;
    }


    public static List<String> getDataFromFile (String filepath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filepath));
        List <String> list = new ArrayList<>();

        String line;
        while ((line=br.readLine()) !=null) {
            list.add(line);
        }
        return list;
    }
}
