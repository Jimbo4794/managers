/*
 * Licensed Materials - Property of IBM
 * 
 * (c) Copyright IBM Corp. 2021.
 */
package dev.galasa.selenium.spi;

import dev.galasa.selenium.Browser;
import dev.galasa.selenium.ISeleniumManager;
import dev.galasa.framework.spi.ResourceUnavailableException;
import dev.galasa.selenium.SeleniumManagerException;

public interface ISeleniumManagerSpi {
    public ISeleniumManager provisionWebDriver(Browser browser) throws ResourceUnavailableException, SeleniumManagerException;
}