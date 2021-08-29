package am.aca.generactive.generactiveservlets.gen.mock;


import am.aca.generactive.generactiveservlets.gen.model.Configuration;
import am.aca.generactive.generactiveservlets.gen.model.Resolution;

import java.util.Random;

public class ConfigurationMock {

    public static Configuration getConfiguration() {
            return new Configuration(Resolution.values()[new Random().nextInt(Resolution.values().length - 1)]);
        }

        private ConfigurationMock() {

        }
}
