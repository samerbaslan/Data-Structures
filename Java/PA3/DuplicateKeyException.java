/* Samer Baslan
 * sbaslan
 * CMPS 12B
 * 07/23/2017
 * PA3
 */

//DuplicateKeyException
//Throws an exception in case of duplicate keys inserted

public class DuplicateKeyException extends RuntimeException {
    
    public DuplicateKeyException(String s) {
        super(s);
    }
}
