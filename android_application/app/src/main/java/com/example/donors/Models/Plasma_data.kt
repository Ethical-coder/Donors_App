package com.example.donors.Models

class plasmaData{


    var name:String="" ;
    var number : String="" ;
    var blood:String="";
    constructor( name:String,number:String ,blood:String){
        this.name=name;
        this.number=number;
        this.blood=blood;
    };

    @JvmName("setName1")
    public fun setName(name:String): Unit{
        this.name=name;
    }
    @JvmName("setNumber1")
    public fun setNumber(number: String):Unit{
        this.number=number;
    }
    @JvmName("setBlood1")
    public fun setBlood(blood: String):Unit{
        this.blood=blood;
    }

    @JvmName("getName1")
    public fun getName():String{
        return this.name;
    }
    @JvmName("getNumber1")
    public fun getNumber():String{
        return this.number;
    }
    @JvmName("getBlood1")
    public fun getBlood():String{
        return this.blood;
    }
}