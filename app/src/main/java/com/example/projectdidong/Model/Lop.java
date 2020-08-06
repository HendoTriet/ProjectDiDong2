package com.example.projectdidong.Model;

import java.util.HashMap;
import java.util.Map;

public class Lop {
    String sTenLop, sID;

    public String getsTenLop ( ) {
        return sTenLop;
    }

    public void setsTenLop ( String sTenLop ) {
        this.sTenLop = sTenLop;
    }

    public String getsID ( ) {
        return sID;
    }

    public void setsID ( ) {
        this.sID = sID;
    }

    public Lop ( String sTenLop , String sID ) {
        this.sTenLop = sTenLop;
        this.sID = sID;
    }

    public Lop ( ) {
    }

    public Lop ( String sTenLop ) {
        this.sTenLop = sTenLop;
    }

    @Override
    public String toString ( ) {
        return "Lop{" +
                "sTenLop='" + sTenLop + '\'' +
                ", sID='" + sID + '\'' +
                '}';
    }


    public Map<String, Object> toMap ( ) {
        HashMap<String, Object> result = new HashMap<> ( );
        result.put ( "idLop" , sID );
        result.put ( "tenLop" , sTenLop );
        return result;
    }


}
