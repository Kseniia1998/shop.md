package com.core;

import com.google.common.collect.Ordering;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Utils {
    public static void waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(WebDriverHelper.getChromeDriver(), Duration.ofMillis(45000L));
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'"));
    }

    public static WebElement waitForElementVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(WebDriverHelper.getChromeDriver(), Duration.ofMillis(45000L));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static <T, F extends Comparable<? super F>> boolean isListSorted(List<T> list, String fieldName, String direction) {
        try {
            // Получение геттера для указанного поля
            Method getter = list.get(0).getClass().getMethod(getGetterMethodName(fieldName));

            // Функция извлечения значения поля из объекта
            Function<T, F> fieldExtractor = obj -> {
                try {
                    @SuppressWarnings("unchecked")
                    F fieldValue = (F) getter.invoke(obj);
                    return fieldValue;
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                    return null;
                }
            };

            // Создание компаратора и проверка сортировки
            Comparator<F> comparator = direction.equals("asc") ? Comparator.naturalOrder() : Comparator.reverseOrder();
            for (int i = 1; i < list.size(); i++) {
                F currentField = fieldExtractor.apply(list.get(i));
                F previousField = fieldExtractor.apply(list.get(i - 1));
                if (comparator.compare(currentField, previousField) < 0) {
                    return false; // Не отсортировано
                }
            }
            return true; // Отсортировано
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String getGetterMethodName(String fieldName) {
        return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }


}
