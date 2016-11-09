package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Александр on 09.11.2016.
 */
public class PrimesTests {

  @Test
  public void  testPrimes(){
    Assert.assertTrue(Primes.isPrimeFast(Integer.MAX_VALUE));
  }

  @Test(enabled = false)
  public void  testPrimesLong(){
    long n = Integer.MAX_VALUE;
    Assert.assertTrue(Primes.isPrime(n));
  }

  @Test
  public void  testNonPrimes(){
    Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE - 2));
  }


}
