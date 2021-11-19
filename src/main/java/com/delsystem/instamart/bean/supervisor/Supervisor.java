package com.delsystem.instamart.bean.supervisor;

import com.delsystem.instamart.bean.PartnerBase;
import com.delsystem.instamart.util.Role;

/**
 * DelSystem Created by Home Work Studio AndrHey [andreigp]
 * FileName: Supervisor.java
 * Date/time: 18 октябрь 2021 in 16:24
 * @author andreigp Andrei G. Pastushenko
 * @copy Can't use code
 */

public class Supervisor extends PartnerBase {

    public Supervisor() {
        this.setRole(Role.SUPERVISOR.getRole());
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
