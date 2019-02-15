package com.breakidea.noah.model;

import java.io.Serializable;
import java.util.Map;

public interface Featureable extends Serializable {

    public boolean clearFeature();

    public Map<String, Object> getFeatureMap();

    public <T> T getFeature(String featureName);

    public <T> T putFeatureIfAbsent(String featureName, T featureValue);

    public <T> T removeFeature(String featureName);

    public <T> T setFeature(String featureName, T featureValue);

    public void setFeatureMap(Map<String, Object> featureMap);

    public void setFeatures(Map<String, Object> featureMap);
}
