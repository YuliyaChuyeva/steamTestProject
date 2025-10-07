package service.pages;

import core.Driver;

public abstract class AbstractPage {
    public String getPageTitle() {
        return Driver.getInstance().getTitle();
    }
}
