package com.qpros.helpers;

import com.qpros.common.DriverType;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.rauschig.jarchivelib.ArchiveFormat;
import org.rauschig.jarchivelib.Archiver;
import org.rauschig.jarchivelib.ArchiverFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WebDriverInstaller {

    public static class WebDriverSetup {

        public static void downloadAndExtractWebDriver(DriverType driverType, String DriverOsType, Archiver archiver) throws IOException {

            System.out.println("TO start downloading webDriver for " + driverType.name());
            switch (driverType) {
                case FIREFOX:
                    HttpResponse<JsonNode> node = Unirest.get("https://api.github.com/repos/mozilla/geckodriver/releases/latest").asJson();
                    JSONObject jsonObject = new JSONObject(node.getBody().toPrettyString());
                    String versionn = jsonObject.get("name").toString();
                    JSONArray assets = jsonObject.getJSONArray("assets");
                    List<String> browserUrl = new ArrayList<>();
                    for (int i = 0; i < assets.length(); i++) {
                        browserUrl.add(new JSONObject(assets.get(i).toString()).get("browser_download_url").toString());

                    }
                    String finalUrl;
                    switch (DriverOsType) {
                        case "windows":
                            finalUrl = browserUrl.parallelStream().filter(x -> x.contains("win64")).collect(Collectors.joining());
                            Unirest.get(finalUrl).asFile("src/main/resources/browserDrivers/firefox_win32.zip");
                            archiver.extract(new File("src/main/resources/browserDrivers/firefox_win32.zip"), new File("src/main/resources/browserDrivers/firefox"));
                            new File("src/main/resources/browserDrivers/firefox_win32.zip").delete();
                            break;
                        case "linux":
                            finalUrl = browserUrl.parallelStream().filter(x -> x.contains("linux64.tar.gz")).collect(Collectors.joining());
                            Unirest.get(finalUrl).asFile("src/main/resources/browserDrivers/firefox_linux64.tar.gz");
                            archiver = ArchiverFactory.createArchiver(ArchiveFormat.TAR);
                            archiver.extract(new File("src/main/resources/browserDrivers/firefox_linux64.tar.gz"), new File("src/main/resources/browserDrivers/firefox"));
                            new File("src/main/resources/browserDrivers/firefox_win32.zip").delete();
                            break;
                    }

                    System.out.println("New Driver has been downloaded for  " + driverType.name() + "  and version " + versionn);

                    break;
                case CHROME:
                    HttpResponse<String> version = Unirest.get("http://chromedriver.storage.googleapis.com/LATEST_RELEASE").asString();
                    String base_url = "https://chromedriver.storage.googleapis.com";
                    String filename, chromedriver_url;
                    switch (DriverOsType) {
                        case "windows":
                            filename = "chromedriver_win32.zip";
                            chromedriver_url = base_url + "/" + version.getBody() + "/" + filename;
                            Unirest.get(chromedriver_url).asFile("src/main/resources/browserDrivers/chromedriver_win32.zip");
                            archiver.extract(new File("src/main/resources/browserDrivers/chromedriver_win32.zip"), new File("src/main/resources/browserDrivers/chromedriver"));
                            new File("src/main/resources/browserDrivers/chromedriver_win32.zip").delete();
                            break;
                        case "linux":
                            filename = "chromedriver_Linux64.zip";
                            chromedriver_url = base_url + "/" + version.getBody() + "/" + filename;
                            Unirest.get(chromedriver_url).asFile("src/main/resources/browserDrivers/chromedriver_Linux64.zip");
                            archiver.extract(new File("src/main/resources/browserDrivers/chromedriver_Linux64.zip"), new File("src/main/resources/browserDrivers/chromedriver"));
                            new File("src/main/resources/browserDrivers/chromedriver_Linux64.zip").delete();
                            break;
                    }
                    System.out.println("New Driver has been downloaded for  " + driverType.name() + "  and version " + version.getBody());


            }

        }


    }


}
