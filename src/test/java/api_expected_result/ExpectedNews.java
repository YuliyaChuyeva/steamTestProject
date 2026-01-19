package api_expected_result;

import service.api_object.AppNews;

public class ExpectedNews {
    public static AppNews oneFingerDeathPunch() {
        AppNews appNews = new AppNews();
        appNews.setAppId(264200);
        return appNews;
    }

    public static int count() {
        return 3;
    }

    public static int maxLength() {
        return 300;
    }
}

