package global;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.logging.Logger;

public class ConfigurationSetting {
    private static ConfigurationSetting instance = null;
    Properties prop;

    private ConfigurationSetting() {
        prop = new Properties();
        try {
            String path = System.getProperty("bridgechinoiseFV.jar");
            System.out.println(path);
            String outpath = "/res/";
            InputStream in = new FileInputStream(new File(System.getProperty("bridgechinoiseFV.jar")+File.separator+"res"+File.separator+"defaultSetting.cfg"));


            //InputStream propIn = new FileInputStream(imgURL);
            prop.load(in);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ConfigurationSetting instance() {
        instance = new ConfigurationSetting();
        return instance;
    }

    public String lis(String cle) throws NoSuchElementException{
        String res = prop.getProperty(cle);
        if(res == null) throw new NoSuchElementException("Prop " + cle + " not defined");
        return res;
    }


}
