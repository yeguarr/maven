import Exceptions.failedCheckException;

public interface Checker<T>
{
    T checker(T t) throws failedCheckException;
}