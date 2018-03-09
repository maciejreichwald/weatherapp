package com.rudearts.dbgenerator;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class GreendaoGenerator {
    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(2, "com.rudearts.weatherapp.model.greendao");

        Entity city = schema.addEntity("CityDb");
        city.setDbName("city");
        city.addIdProperty();
        city.addStringProperty("name");
        city.addStringProperty("country");
        city.addDoubleProperty("latitude");
        city.addDoubleProperty("longitude");

        Entity weather = schema.addEntity("WeatherDb");
        weather.addIdProperty();
        weather.addIntProperty("cod");
        weather.addIntProperty("main");
        weather.addIntProperty("description");
        weather.addIntProperty("icon");
        weather.addDoubleProperty("temperature");
        weather.addDoubleProperty("pressure");
        weather.addDoubleProperty("humidity");

        DaoGenerator generator = new DaoGenerator();
        generator.generateAll(schema, "../model/src/main/java");
    }
}
