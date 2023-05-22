package com.alame.lab6.common.parsers;

import com.alame.lab6.common.data.KeyValidator;
import com.alame.lab6.common.exceptions.IncorrectKeyException;

/**
 * class for parsing key from string
 */
public class KeyParser{
    private final KeyValidator keyValidator;

    public KeyParser(KeyValidator keyValidator) {
        this.keyValidator = keyValidator;
    }

    /**
     * parse key from string
     * @param s - string to parse
     * @return key
     * @throws IncorrectKeyException if key is not valid
     */
    public String parseKey(String s) throws IncorrectKeyException {
        if (!(keyValidator.validKey(s))) throw new IncorrectKeyException("ключ не должен быть null");
        return s;
    }
}
