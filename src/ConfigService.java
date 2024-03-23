import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigService {
    private final Properties configProperties = new Properties();

    public ConfigService() {
        loadConfigProperties();
    }

    private void loadConfigProperties() {
        try (InputStream fis = new FileInputStream("config.properties")){
            configProperties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isAllowDeletePetHouse() {
        return Boolean.parseBoolean(configProperties.getProperty("allowDeletePetHouse"));
    }

    public int getMaxCapacity() {
        return Integer.parseInt(configProperties.getProperty("maxCapacity"));
    }
}