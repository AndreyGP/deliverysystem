package com.delsystem.instamart.bean.seniorpicker;

import com.delsystem.instamart.bean.PartnerBase;
import com.delsystem.instamart.util.Role;

/**
 * DelSystem Created by Home Work Studio AndrHey [andreigp]
 * FileName: Picker.java
 * Date/time: 18 октябрь 2021 in 16:23
 * @author andreigp Andrei G. Pastushenko
 * @copy Can't use code
 */

public class Picker extends PartnerBase {

    public Picker() {
        this.setRole(Role.PICKER.getRole());
    }

    @Override
    public String getPartnerInfo() {
        readLock.lock();
        try {
            return null;
        }finally {
            readLock.unlock();
        }
    }

    @Override
    public void dumpPartnerInfo() {
        readLock.lock();
        try {
            int i = 0;
        }finally {
            readLock.unlock();
        }
    }

    @Override
    public void backupPartnerInfo() {
        writeLock.lock();
        try {
            int i = 0;
        }finally {
            writeLock.unlock();
        }
    }
}
