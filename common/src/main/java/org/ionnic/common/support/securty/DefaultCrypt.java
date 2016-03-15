package org.ionnic.common.support.securty;

/**
 * @author apple
 *
 */
public final class DefaultCrypt {

    private String key;

    /**
     * @param key
     */
    public DefaultCrypt(String key) {
        this.key = key;
    }

    /**
     * @param data
     * @return
     * @throws Exception
     */
    public String decrypt(String data) throws Exception {
        return Crypt.decrypt(data, key);
    }

    /**
     * @param data
     * @return
     * @throws Exception
     */
    public String encrypt(String data) throws Exception {
        return Crypt.encrypt(data, key);
    }
}
