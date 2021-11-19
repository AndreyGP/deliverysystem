package com.delsystem.instamart.bean.courier;

import com.delsystem.instamart.bean.PartnerBase;
import com.delsystem.instamart.util.Role;

/**
 * DelSystem Created by Home Work Studio AndrHey [andreigp]
 * FileName: Courier.java
 * Date/time: 18 октябрь 2021 in 16:17
 * @author andreigp Andrei G. Pastushenko
 * @copy Can't use code
 */

public class Courier extends PartnerBase {

    public Courier() {
        this.setRole(Role.COURIER.getRole());
    }

    @Override
    public String getPartnerInfo() {
        return null;
    }

    @Override
    public void dumpPartnerInfo() {

    }

    @Override
    public void backupPartnerInfo() {

    }
}
