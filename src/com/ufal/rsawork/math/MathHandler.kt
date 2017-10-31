package com.ufal.rsawork.math

class MathHandler

/**
 * It gets a number and check
 * if it's prime or not.
 *
 * @param number value
 * @return true if is prime, false if not
 */
fun isPrime(number: Long): Boolean {
    if(number <= 1)
        return false
    var i = 2L
    while(i <= Math.sqrt(number.toDouble())) {
        if((number % i) == 0L)
            return false
        i++
    }
    return true
}

/**
 * It gets two numbers and returns its
 * inverse.
 *
 * OBS: -505 param is language requirement
 *
 * @param e key
 * @param totient value
 * @return inverse
 */
fun extendedEuclides(e: Long, totient: Long): Long {
    for(i in 1..totient)
        if((i * e) % totient == 1L)
            return i
    return -505
}

/**
 * It gets two integers and returns its totient.
 *
 * @param p key
 * @param q key
 * @return totient
 */
fun totient(p: Long, q: Long): Long {
    return (p - 1) * (q - 1)
}

/**
 * It gets two numbers and returns its gcd.
 *
 * @param a number
 * @param b number
 * @return gcd between them
 * */
fun gcd(a: Long, b: Long): Long {
    if(b == 0L)
        return a
    return gcd(b, a % b)
}

/**
 * It gets two number and check if their gcd
 * is equals to one.
 *
 * @param e key
 * @param totient value
 * @return true if gcd == 1, false if not
 */
fun isCoPrime(e: Long, totiente: Long): Boolean {
    return gcd(e, totiente) == 1L
}

/**
 * It gets a totient and returns list
 * of coprimes.
 *
 * @param totient value
 * @return list of coprimes
 */
fun getCoprimes(totient: Long): MutableList<Long> {
    val numbers: MutableList<Long> = mutableListOf()
    var counter: Int = 0
    for(i in 2..(totient))
        if(isCoPrime(i, totient)) {
            numbers.add(i)
            counter++
            if(counter == 10)
                break
        }
    return numbers.subList(0, 10)
}

/**
 * It gets three arguments: ASCII, e, and number n.
 *
 * @param ASCII
 * @param e key or d inverse
 * @param n Key
 * @return encrypted char or desencrypted char
 */
fun fastModularExponentiation(ASCII: Long, e: Long, N: Long): Long {
    var ASCII = ASCII
    var e = e
    var r: Long = 1L
    ASCII = ASCII % N
    while (e > 0L) {
        if (e % 2L != 0L)
            r = (r * ASCII) % N
        e /= 2L
        ASCII = (ASCII * ASCII) % N
    }
    return r
}