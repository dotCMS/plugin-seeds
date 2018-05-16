package com.dotcms.visitor.character;

import com.dotcms.visitor.filter.characteristics.AbstractCharacter;

/**
 * @author nollymar
 * Date: 5/16/18
 */
public class CustomCharacter extends AbstractCharacter {

    public CustomCharacter(AbstractCharacter incomingCharacter) {
        super(incomingCharacter);

        getMap().put("custom_key_test", "Custom character added");
    }
}
