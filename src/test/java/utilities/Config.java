package utilities;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    static Properties prop;
    static ManagedChannel channel;
    InputStream input;

    public Config() {
        input = Config.class.getClassLoader().getResourceAsStream("config.properties");
        prop = new Properties();
        try {
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getIP(String service) {
        String ip = prop.getProperty(service.toLowerCase() + "_ip");
        if (ip != null) return ip;
        else throw new RuntimeException(service + "_ip value is not specified in the config.properties file.");
    }

    public static int getPort(String service) {
        int port = Integer.parseInt(prop.getProperty(service.toLowerCase() + "_port"));
        if (port != 0) return port;
        else throw new RuntimeException(service + "_port value is not specified in the config.properties file.");
    }

    public static ManagedChannel getValues(String service) {
        new Config();
        String ip = getIP(service);
        int port = getPort(service);
        channel = ManagedChannelBuilder.forAddress(ip, port)
                .usePlaintext()
                .build();
        return channel;
    }
}
