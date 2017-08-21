package com.dotcms.plugin.aop.rest;

import com.dotcms.content.elasticsearch.business.ESContentFactoryImpl;
import com.dotmarketing.exception.DotDataException;

import java.util.Date;


public class MyESContentFactoryImpl extends ESContentFactoryImpl {

    public int deleteContentFrom(final Date deleteFrom) throws DotDataException {

        return this.deleteOldContent(deleteFrom);
    }
}
