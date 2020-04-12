package Utils;

import Utils.InternalClasses.CustomDate;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.UUID;

//@Log4j
public class Utils {

    public static String getSpecificDate(String date) {
        if (date.startsWith("###")) {
            String passedDate = date.replace("###", "");
            Gson gson = new Gson();
            CustomDate customDate = gson.fromJson(passedDate, CustomDate.class);
            LocalDateTime now = LocalDateTime.now().plusMonths(customDate.month).plusDays(customDate.day);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(customDate.pattern).withLocale(Locale.ENGLISH);
            return now.format(formatter);
        }
        return date;
    }

    public static String convertAmount(String amount) {
        String result = String.format(Locale.UK, "%,.2f", Double.valueOf(amount)).replaceAll(",", " ");
        //log.debug("Converted amount is " + result);
        return result;

    }

    /**
     * @param data $$${"decimalSeparator": ".", "groupingSeparator": " ", "amount": "1000,25"}
     *             $$${"decimalSeparator": ".", "groupingSeparator": " ", "amount": "1000,25", "pattern": "###,###"}
     * @return result
     */
    public static String convertAmountWithPattern(String data) {
        String result = data;
        DecimalFormatSymbols customSymbol = new DecimalFormatSymbols();
        if (data.startsWith("$$$")) {
            String dataJson = data.replace("$$$", "");
            JSONObject json = new JSONObject(dataJson);
            String defaultPattern = "###,###.00";
            String pattern;
            try {
                pattern = json.getString("pattern");
            } catch (Exception ignore) {
                pattern = defaultPattern;
            }
            DecimalFormat df = new DecimalFormat(pattern);
            if (json.getString("amount").startsWith("<<<")) {
                return result;
            }
            if (!json.getString("decimalSeparator").isEmpty()) {
                customSymbol.setDecimalSeparator(json.getString("decimalSeparator").charAt(0));
            }
            if (!json.getString("groupingSeparator").isEmpty()) {
                customSymbol.setGroupingSeparator(json.getString("groupingSeparator").charAt(0));
            }
            df.setDecimalFormatSymbols(customSymbol);
            df.setGroupingUsed(true);

            result = df.format(Double.parseDouble(json.getString("amount")
                    .replaceAll(",", ".")
                    .replaceAll("\\s+", "")
                    .replaceAll("\u00a0", " ")
            ));
            //log.debug("Converted amount is " + result);
        }
        return result;
    }

    public static void saveFile(URL url, String imgSavePath, String token) {
        boolean isSucceed = true;
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(url.toString());
        httpGet.addHeader("Authorization", token);

        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity imageEntity = httpResponse.getEntity();
            if (imageEntity != null) {
                FileUtils.copyInputStreamToFile(imageEntity.getContent(), new File(imgSavePath));
            }
        } catch (IOException e) {
            isSucceed = false;
        }
        httpGet.releaseConnection();
    }

    public File renameFile(String pathToFile, String oldName, String newName) {
        File file = new File(pathToFile + oldName);
        file.renameTo(new File(pathToFile + newName));
        //log.debug("Renamed file is " + file);
        return file;
    }

    public String generateUUID(String value) {
        if (value.startsWith("%") && value.endsWith("%")) {
            if (value.equals("%RANDOM_UUID%")) {
                value = UUID.randomUUID().toString();
            } else {
                throw new UnsupportedOperationException("Randomization is not suipported: " + value);
            }
        }
        return value;
    }

    public String generateRandomNumber() {
        SecureRandom random = new SecureRandom();
        int num = random.nextInt(10000);
        String result = String.format("%05d", num);
        //log.debug("Result is " + result);
        return result;
    }

    public File getFileFromDirectoryByIndex(String directory, int index, String fileNameContains) {
        File dir = new File(directory);
        File[] matches = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(fileNameContains);
            }

        });
        return matches[index];
    }
}
