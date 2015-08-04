package com.baselogic.tutorials.service;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author xpoft
 */
@Component
public class ApplicationCounter implements Serializable
{
    private int count = 0;

    public int getCount()
    {
        return ++count;
    }
}
