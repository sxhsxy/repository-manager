package sample;

import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.util.Callback;
/**
 * Created by xiaohu on 14-6-3.
 */
public abstract class FXMLView {

        public static final String DEFAULT_ENDING = "view";
        protected FXMLLoader loader;

        public FXMLView() {
            this.init(getClass(), getFXMLName());
        }

        private void init(Class clazz, String conventionalName) {
            final URL resource = clazz.getResource(conventionalName);
            this.loader = new FXMLLoader(resource);
            this.loader.setControllerFactory(new Callback<Class<?>, Object>() {
                @Override
                public Object call(Class<?> p) {
                    return SpringContextProvider.getApplicationContext().getBean(p);
                }
            });
            try {
                loader.load();
            } catch (Exception ex) {
                throw new IllegalStateException("Cannot load " + conventionalName, ex);
            }
        }

        public Parent getRoot() {
            Parent parent = this.loader.getRoot();
            addCSSIfAvailable(parent);
            return parent;
        }

        void addCSSIfAvailable(Parent parent) {
            URL uri = getClass().getResource(getStyleSheetName());
            if (uri == null) {
                return;
            }
            String uriToCss = uri.toExternalForm();
            parent.getStylesheets().add(uriToCss);
        }

        String getStyleSheetName() {
            return getConventionalName(".css");
        }

        public Object getController() {
            Object controller = this.loader.getController();
            return controller;
        }

        String getConventionalName(String ending) {
            String clazz = this.getClass().getSimpleName().toLowerCase();
            return stripEnding(clazz) + ending;
        }

        static String stripEnding(String clazz) {
            if (!clazz.endsWith(DEFAULT_ENDING)) {
                return clazz;
            }
            int endingIndex = clazz.lastIndexOf(DEFAULT_ENDING);
            return clazz.substring(0, endingIndex);
        }

        final String getFXMLName() {
            return getConventionalName(".fxml");
        }


}
