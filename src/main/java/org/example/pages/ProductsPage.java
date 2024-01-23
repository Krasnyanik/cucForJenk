package org.example.pages;

import io.cucumber.java.bg.И;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;

public class ProductsPage extends BasePage{

    @FindBy(xpath = "//button[contains(text(), 'Добавить')]")
    private WebElement btnAdd;


    @FindBy(xpath = "//form[@id = 'editForm']/*/input[@id = 'name']")
    private WebElement dialogFieldName;

    @FindBy(xpath = "//form[@id = 'editForm']/*/select[@id = 'type']")
    private WebElement dialogSelectType;

    @FindBy(xpath = "//form[@id = 'editForm']/*/select[@id = 'type']/*")
    private List<WebElement> listSelectTypeSubMenu;


    @FindBy(id = "save")
    private WebElement btnSave;

    @FindBy(xpath = "//table[@class = 'table']/tbody/*")
    private List<WebElement> listRowsTable;




    /**
     * Клик по кнопке "Добавить"
     *
     * @return ProductsPage - т.е. остаемся на этой странице
     */
    @И("нажать на кнопку добавить")
    public ProductsPage clickBtnAdd() {
        waitUtilElementToBeClickable(btnAdd).click();
        return this;
    }

    /**
     * Проверка, что вписанное значение в поле ввода действительно такое
     *
     * @return ProductsPage - т.е. остаемся на этой странице
     */
    @И("проверка, что значение в поле ввода равно {string}")
    public ProductsPage checkField(String text){
        fillInputField(dialogFieldName, text);
        Assertions.assertEquals(text, dialogFieldName.getAttribute("value"),
                "Значение поля не соответствует введенному");
        System.out.println("Полученный текст из поля ввода: " +  dialogFieldName.getAttribute("value"));
        return this;
    }

    /**
     * Функция клика на любое подменю выпадающего списка в окне добавления товара
     *
     * @param nameSubMenu - наименование подменю
     * @return ProductsPage - т.е. переходим на страницу {@link ProductsPage}
     */
    @И("выбор типа товара {string}")
    public ProductsPage selectSubMenuSelect(String nameSubMenu) {
        for (WebElement menuItem : listSelectTypeSubMenu) {
            if (menuItem.getText().equalsIgnoreCase(nameSubMenu)) {
                waitUtilElementToBeClickable(menuItem).click();
                return this;
            }
        }
        Assertions.fail("Подменю '" + nameSubMenu + "' не было найдено в окне добавления товара!");
        return this;
    }

    /**
     * Проверка, что в выпадающем списке выбрано именно нужное значение
     *
     * @return ProductsPage - т.е. остаемся на этой странице
     */
    @И("проверка, что в списке выбрано нужное значение {string}")
    public ProductsPage checkTypeSelected(String selectedValue){
        Assertions.assertEquals(selectedValue, dialogSelectType.getAttribute("value"),
                "Значение не соответствует выбранному");
        System.out.println("В списке выбрано значение: " + dialogSelectType.getAttribute("value"));
        return this;
    }



    /**
     * Клик по кнопке "Сохранить"
     *
     * @return ProductsPage - т.е. остаемся на этой странице
     */
    @И("нажать на кнопку сохранить")
    public ProductsPage clickBtnSave() {
        waitUtilElementToBeClickable(btnSave).click();
        return this;
    }



    @И("экзотичность {string}")
    public ProductsPage checkNewAddToTable(String isExoticStr){
        boolean isExotic = Boolean.parseBoolean(isExoticStr);

        WebElement rowTable = listRowsTable.get(listRowsTable.size() - 1);
        List<String> elementsOfRowListExpected = Arrays.asList(String.valueOf(isExotic));
        boolean allEqual = true;

        String strWithOutId = rowTable.getText().substring(2);

        List<String> elementsOfRowList = Arrays.asList(strWithOutId.split(" ", -1));

        for (int i = 0; i < elementsOfRowList.size(); i++) {
            if (!elementsOfRowList.get(i).equals(elementsOfRowListExpected.get(i))) {
                allEqual = false;
                break;
            }
        }
        if (!allEqual){
            Assertions.fail("Элементы не совпадают!" +
                    "\nСами элементы:\t\t" + strWithOutId +
                    "\nПереданная строка:\t" + String.join(" ", elementsOfRowListExpected));
        }

        return this;
    }



}
