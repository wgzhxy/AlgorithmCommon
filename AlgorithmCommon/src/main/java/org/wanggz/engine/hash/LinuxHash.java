package org.wanggz.engine.hash;

import java.util.ArrayList;
import java.util.List;

/**
 * 分层离散数据, 做到hash的平均分布
 * 
 * @author guangzhong.wgz
 */
public class LinuxHash {

    static long hashLong(long val, int bits) {
        long hash = val * 0x9e370001L;
        return hash >> (32 - bits);
    }

    public static void main(String[] args) {

        System.out.println("Check user distribution of layer24:");
        int[] layer24Distribution = new int[20];
        for (int i = 0; i < 20; ++i) {
            layer24Distribution[i] = 0;
        }

        //记录第八桶数据
        List<Integer> uidOfLayer24Bucket9 = new ArrayList<Integer>();
        for (int i = 0; i < 10000; i++) {
            int uid = 10000 + i;
            int lid = 24;
            int num = uid * lid;
            int hashValue = (int) hashLong(num, 8);
            int bucket = hashValue % 20;
            if (bucket == 8) {
                uidOfLayer24Bucket9.add(uid);
            }
            layer24Distribution[bucket]++;
        }
        for (int i = 1; i <= 20; i++) {
            System.out.println("user amount of layer24-bucket" + i + ":" + layer24Distribution[i - 1]);
        }

        //第八桶数据再进行离散
        System.out.println("Check user distribution of layer25 from layer24-bucket9:");
        int[] layer25FromLayer24Bucket9Distribution = new int[20];
        for (Integer uid : uidOfLayer24Bucket9) {
            int lid = 25;
            int num = uid * lid;
            int hashValue = (int) hashLong(num, 8);
            int bucket = hashValue % 20;
            layer25FromLayer24Bucket9Distribution[bucket]++;
        }
        for (int i = 1; i <= 20; i++) {
            System.out.println("user amount of layer25-bucket" + i + " from layer24-bucket9:"
                    + layer25FromLayer24Bucket9Distribution[i - 1]);
        }

    }
}
