package com.hsai.myhashmap.interfaces;

import java.util.Optional;

import com.hsai.myhashmap.LookupStrategy;
import com.hsai.mypoint.*;
import com.hsai.myhashmap.exceptions.*;

public interface HashMapInterface {
    public void put(String key, MyPoint val);

    public MyPoint get(String key);

    public MyPoint getOrElse(String key, MyPoint val);

    public Optional<MyPoint> getSafe(String key);

    public boolean contains(String key);

    public Optional<MyPoint> remove(String key);

    public void setStrategy(LookupStrategy strategy) throws IncorrectStrategyException;

    public void setStep(int step) throws IncorrectStepException;

    public void clear();

    public int getSize();
}
