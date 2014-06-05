package sample;

/**
 * Created by xiaohu on 14-6-2.
 */
import javafx.fxml.FXMLLoader;
import javafx.util.Callback;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.io.InputStream;

public class SpringFxmlLoader
{
    private ApplicationContext context;

    public SpringFxmlLoader(ApplicationContext context)
    {
        this.context = context;
    }

    public Object load(String url, Class<?> controllerClass) throws IOException
    {
        InputStream fxmlStream = null;
        try
        {
            fxmlStream = controllerClass.getResourceAsStream(url);
            Object instance = context.getBean(controllerClass);
            FXMLLoader loader = new FXMLLoader();
            Callback controllerFactory  = loader.getControllerFactory();
            loader.getNamespace().put("controller", instance);

            return loader.load(fxmlStream);
        }
        finally
        {
            if (fxmlStream != null)
            {
                fxmlStream.close();
            }
        }
    }
}
